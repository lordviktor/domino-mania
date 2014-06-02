package br.com.liviazilberberg.dominomania.client.service;

import br.com.liviazilberberg.dominomania.client.model.Player;

public class GameTurnEvent {

	private Player nextPlayer;


	public GameTurnEvent(Player nextPlayer) {
		super();
		this.nextPlayer = nextPlayer;
	}

	public Player getNextPlayer() {
		return nextPlayer;
	}

	public void setNextPlayer(Player nextPlayer) {
		this.nextPlayer = nextPlayer;
	}

}
