package br.com.liviazilberberg.dominomania.client.service.event;

import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.DominoBrick.Side;
import br.com.liviazilberberg.dominomania.client.model.Player;

public class GameTurnMoveEvent {

	private Player player;

	private DominoBrick move;

	private Side side;

	public GameTurnMoveEvent(Player player, DominoBrick move, Side side) {
		super();
		this.player = player;
		this.move = move;
		this.side = side;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public DominoBrick getMove() {
		return move;
	}

	public void setMove(DominoBrick move) {
		this.move = move;
	}
}
