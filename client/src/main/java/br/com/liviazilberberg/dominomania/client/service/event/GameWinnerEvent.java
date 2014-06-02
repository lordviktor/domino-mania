package br.com.liviazilberberg.dominomania.client.service.event;

import br.com.liviazilberberg.dominomania.client.model.Player;

public class GameWinnerEvent {

	private Player winner;

	public Player getWinner() {
		return winner;
	}

	public void setWinner(Player winner) {
		this.winner = winner;
	}
}
