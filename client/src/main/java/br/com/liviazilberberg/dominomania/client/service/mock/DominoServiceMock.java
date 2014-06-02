package br.com.liviazilberberg.dominomania.client.service.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.DominoBrick.Side;
import br.com.liviazilberberg.dominomania.client.model.Player;
import br.com.liviazilberberg.dominomania.client.service.DominoService;
import br.com.liviazilberberg.dominomania.client.service.GameTurnListener;
import br.com.liviazilberberg.dominomania.client.service.event.GameTurnEvent;
import br.com.liviazilberberg.dominomania.client.service.event.GameTurnMoveEvent;

public class DominoServiceMock implements DominoService {
	private List<DominoBrick> dominoBricks;
	private List<DominoBrick> dominoOnTable;
	private List<GameTurnListener> gameTurnListeners;
	private List<Player> players;

	public DominoServiceMock(Player player) {
		this.dominoBricks = new ArrayList<DominoBrick>();
		this.players = new ArrayList<Player>();
		this.dominoOnTable = new ArrayList<DominoBrick>();
		this.gameTurnListeners = new ArrayList<GameTurnListener>();

		for (int i = 0; i < 3; i++) {
			Player mockPlayer = new MockPlayer(this);
			mockPlayer.setNickname(UUID.randomUUID().toString());
			players.add(mockPlayer);
		}

		this.players.add(player);

		for (int leftSide = 0; leftSide < 7; leftSide++) {
			for (int RigthSide = 0; RigthSide <= leftSide; RigthSide++) {
				this.dominoBricks.add(new DominoBrick(leftSide, RigthSide));
			}
		}
	}

	@Override
	public void ready() {
		new Thread(new MockPlayerHandler(this)).start();
		Player nextPlayer = drawsFirstPlayer();
		notifyGameTurn(new GameTurnEvent(nextPlayer));
	}

	@Override
	public DominoBrick drawsDominoBrick() {
		if (dominoBricks.size() == 0) {
			return null;
		}
		int sortedBrick = new Random().nextInt(dominoBricks.size());
		DominoBrick result = dominoBricks.remove(sortedBrick);
		return result;
	}

	@Override
	public List<DominoBrick> listDominosOnTable() {
		return dominoOnTable;
	}

	@Override
	public List<DominoBrick> listDominosOnPlayerHand() {
		List<DominoBrick> result = new ArrayList<DominoBrick>();
		for (int i = 0; i < 7; i++) {
			result.add(drawsDominoBrick());
		}

		return result;
	}

	@Override
	public void addGameTurnListener(GameTurnListener listener) {
		this.gameTurnListeners.add(listener);
	}

	@Override
	public void playDominoBrick(DominoBrick dominoBrick, Player player) {
		if (dominoBrick != null) { // caso o jogador nao tem pecas para jogar
			if (dominoOnTable.size() == 0) {
				dominoOnTable.add(dominoBrick);
			} else if (dominoOnTable.get(0).verifyCompatibility(dominoBrick, Side.LEFT)) {
				dominoOnTable.add(0, dominoBrick);
			} else if (dominoOnTable.get(dominoOnTable.size() - 1).verifyCompatibility(dominoBrick, Side.RIGTH)) {
				dominoOnTable.add(dominoBrick);
			}
		}

		notifyGameMove(new GameTurnMoveEvent(player, dominoBrick));
		notifyGameTurn(new GameTurnEvent(calculateNextPlayer(player)));
	}

	@Override
	public List<Player> listPlayers() {
		return this.players;
	}

	private Player calculateNextPlayer(Player currentPlayer) {
		if (players.indexOf(currentPlayer) == players.size() - 1) {
			return players.get(0);
		} else {
			return players.get(players.indexOf(currentPlayer) + 1);
		}
	}

	public void notifyGameTurn(GameTurnEvent gameTurnEvent) {
		for (GameTurnListener listener : gameTurnListeners) {
			listener.turnBegin(gameTurnEvent);
		}
	}

	public void notifyGameMove(GameTurnMoveEvent gameTurnMoveEvent) {
		for (GameTurnListener listener : gameTurnListeners) {
			listener.turnEnd(gameTurnMoveEvent);
		}
	}

	private Player drawsFirstPlayer() {
		int firstPlayer = new Random().nextInt(players.size());
		Player nextPlayer = players.get(firstPlayer);
		return nextPlayer;
	}
}
