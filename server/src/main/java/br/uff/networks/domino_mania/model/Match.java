package br.uff.networks.domino_mania.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import br.uff.networks.NetworkCommand;
import br.uff.networks.Transport;

public class Match implements Runnable {

	private static final int INITIAL_HAND = 7;
	private final Deck deck;
	private final Player[] players;
	private final Table table;
	private final Map<String, NetworkCommand> protocol;
	private Player currentPlayer;

	public Match(Transport[] clients) {
		deck = new Deck();
		table = new Table();
		players = createPlayers(clients);
		protocol = new HashMap<>();
		protocol.put(MessagesToServer.PLAY_RIGHT, new PlayRight(this));
	}
	
	Deck getDeck(){
		return deck;
	}
	
	Player[] getPlayers(){
		return players;
	}
	
	Table getTable(){
		return table;
	}
	
	Player getCurrentPlayer(){
		return currentPlayer;
	}
	
	private Player[] createPlayers(Transport[] connections) {
		Player[] players = new Player[connections.length];
		for (int i = 0; i < players.length; i++) {
			Player newPlayer = new Player(readName(connections[i]), new TileContainer(), connections[i]);
			newPlayer.getHand().addAll(drawInitial());
			players[i] = newPlayer;
		}
		Arrays.sort(players, new HandCriteria());
		return players;
	}

	private Collection<Tile> drawInitial() {
		Collection<Tile> hand = new ArrayList<>(INITIAL_HAND);
		for (int i = 0; i < INITIAL_HAND; i++)
			hand.add(deck.draw());
		return hand;
	}

	private String readName(Transport client) {
		return client.receive();
	}

	@Override
	public void run() {
		Iterator<Player> playerIterator = new CyclicIterator<>(players);
		Player winner = null;
		while (winner == null) {
			currentPlayer = playerIterator.next();
			nextTurn(currentPlayer);
			winner = getWinner();
		}
		gameOver(winner);
		closeConnections();
	}

	private void nextTurn(Player currentPlayer) {
		updateClientData(currentPlayer);
		Transport transport = currentPlayer.getTransport();
		String message = transport.receive();
		protocol.get(message).execute(message, transport);
	}

	private void updateClientData(Player currentPlayer) {
		String tableJSON = table.toJSON();
		for (Player player : players) {
			Transport connection = player.getTransport();
			String turnMessage = player == currentPlayer ? MessagesToClient.YOUR_TURN
					: MessagesToClient.turn(player.getName());
			String handJson = player.getHand().toJSON();
			connection.send(turnMessage);
			connection.send(handJson);
			connection.send(tableJSON);
		}
	}

	private Player getWinner() {
		for (Player player : players)
			if (player.getHand().isEmpty())
				return player;
		return null;
	}

	private void gameOver(Player winner) {
		for (Player player : players) {
			Transport connection = player.getTransport();
			connection.send(MessagesToClient.gameOver(winner.getName()));
		}
	}

	private void closeConnections() {
		for (Player player : players) {
			try {
				player.getTransport().close();
			} catch (IOException ex) {
				System.err.println(ex);
			}
		}
	}
}
