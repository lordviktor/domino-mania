package br.com.liviazilberberg.dominomania.client.service.event;

import br.com.liviazilberberg.dominomania.client.model.Player;

public class GameTurnEvent {

	private Player player;

	public GameTurnEvent(Player nextPlayer) {
		super();
		this.player = nextPlayer;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}
