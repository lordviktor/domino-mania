package br.com.liviazilberberg.dominomania.client.service.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.DominoBrick.Side;
import br.com.liviazilberberg.dominomania.client.model.Player;
import br.com.liviazilberberg.dominomania.client.service.DominoService;
import br.com.liviazilberberg.dominomania.client.service.GameTurnEvent;
import br.com.liviazilberberg.dominomania.client.service.GameTurnListener;

public class DominoServiceMock implements DominoService {
	private List<DominoBrick> dominoBricks;
	private List<DominoBrick> dominoOnTable;
	private List<GameTurnListener> gameTurnListeners;
	private List<Player> players;
	private Player currentPlayer;

	private Thread mockPlayersThread;

	public DominoServiceMock(Player player) {
		this.dominoBricks = new ArrayList<DominoBrick>();
		this.players = new ArrayList<Player>();
		this.dominoOnTable = new ArrayList<DominoBrick>();
		this.gameTurnListeners = new ArrayList<GameTurnListener>();

		createMockPlayers();
		this.currentPlayer = players.get(0); // comeca sempre do segundo
		this.players.add(player);

		for (int leftSide = 0; leftSide < 7; leftSide++) {
			for (int RigthSide = 0; RigthSide < 7; RigthSide++) {
				this.dominoBricks.add(new DominoBrick(leftSide, RigthSide));
			}
		}

		runNextPlayer();
	}

	private void runNextPlayer() {
		Player nextPlayer = getNextPlayer();
		if (nextPlayer instanceof MockPlayer) {
			MockPlayer nextMockPlayer = (MockPlayer) nextPlayer;
			this.mockPlayersThread = new Thread(nextMockPlayer);
			this.mockPlayersThread.run();
		}
	}

	@Override
	public DominoBrick drawDominoBrick() {
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
			result.add(drawDominoBrick());
		}

		return result;
	}

	@Override
	public void addGameTurnListener(GameTurnListener listener) {
		this.gameTurnListeners.add(listener);
	}

	@Override
	public void playDominoBrick(DominoBrick dominoBrick, Player player) {
		if (dominoOnTable.size() == 0) {
			dominoOnTable.add(dominoBrick);
		} else if (dominoOnTable.get(0).verifyCompatibility(dominoBrick, Side.LEFT)) {
			dominoOnTable.add(0, dominoBrick);
		} else if (dominoOnTable.get(dominoOnTable.size() - 1).verifyCompatibility(dominoBrick, Side.RIGTH)) {
			dominoOnTable.add(dominoBrick);
		}

		Player nextPlayer = getNextPlayer();

		notifyAll(new GameTurnEvent(nextPlayer));

		runNextPlayer();
	}

	private Player getNextPlayer() {
		if (this.players.indexOf(currentPlayer) == players.size() - 1) {
			return players.get(0);
		} else {
			return players.get(players.indexOf(currentPlayer) + 1);
		}
	}

	private void createMockPlayers() {
		for (int i = 0; i < 3; i++) {
			Player mockPlayer = new MockPlayer(this);
			mockPlayer.setNickname(String.valueOf(System.currentTimeMillis()));
			players.add(mockPlayer);
		}
	}

	public void notifyAll(GameTurnEvent gameTurnEvent) {
		for (GameTurnListener listener : gameTurnListeners) {
			listener.turnOccurred(gameTurnEvent);
		}
	}
}
