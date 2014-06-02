package br.com.liviazilberberg.dominomania.client.service.mock;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import br.com.liviazilberberg.dominomania.client.model.Player;
import br.com.liviazilberberg.dominomania.client.service.GameTurnListener;
import br.com.liviazilberberg.dominomania.client.service.event.GameTurnEvent;
import br.com.liviazilberberg.dominomania.client.service.event.GameTurnMoveEvent;
import br.com.liviazilberberg.dominomania.client.service.event.GameWinnerEvent;

public class MockPlayerHandler implements Runnable, GameTurnListener {

	private DominoServiceMock dominoServiceMock;

	private boolean gameEnded;

	private volatile Player playerHowTurnBegin;

	public MockPlayerHandler(final DominoServiceMock dominoServiceMock) {
		this.dominoServiceMock = dominoServiceMock;
		this.dominoServiceMock.addGameTurnListener(this);
	}

	@Override
	public void run() {
		ExecutorService playersThreadPool = Executors.newFixedThreadPool(1);

		while (!gameEnded) {
			if (playerHowTurnBegin != null && playerHowTurnBegin instanceof MockPlayer) {

				MockPlayer nextMockPlayer = (MockPlayer) playerHowTurnBegin;
				playerHowTurnBegin = null;

				try {
					Future<Void> result = playersThreadPool.submit(nextMockPlayer);
					result.get();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} catch (ExecutionException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void turnBegin(GameTurnEvent event) {
		playerHowTurnBegin = event.getPlayer();
	}

	@Override
	public void turnEnd(GameTurnMoveEvent event) {

	}

	@Override
	public void gameEnd(GameWinnerEvent event) {
		gameEnded = true;
	}
}
