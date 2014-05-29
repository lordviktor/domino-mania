package br.uff.networks.domino_mania;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

import br.uff.networks.Transport;
import br.uff.networks.Observer;
import br.uff.networks.domino_mania.model.Match;

public class MatchStarter implements Observer<Transport> {

	private static final int MATCH_SIZE = 4;

	private final Queue<Transport> clients;

	public MatchStarter() {
		clients = new LinkedList<>();
	}

	@Override
	public synchronized void update(Transport obj) {
		clients.add(obj);
		nextMatch();
	}

	private void nextMatch() {
		if (clients.size() >= 4)
			new Thread(new Match(nextClients())).start();
	}

	private Transport[] nextClients() {
		Transport[] nexts = new Transport[MATCH_SIZE];
		int counter = 0;
		while (counter < MATCH_SIZE) {
			Transport client = clients.poll();
			try {
				client.open();
				nexts[counter++] = client;
			} catch (IOException e) {
				// unable to open client, skip to the next one.
			}
		}
		return nexts;
	}
}
