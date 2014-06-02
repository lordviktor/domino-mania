package br.com.liviazilberberg.dominomania.client.service.mock;

import java.util.ArrayList;
import java.util.List;

import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.DominoBrick.Side;
import br.com.liviazilberberg.dominomania.client.model.Player;

public class MockPlayer extends Player implements Runnable {

	private DominoServiceMock dominoServiceMock;

	private boolean firstTime = true;

	private List<DominoBrick> dominoBricksOnHand = new ArrayList<DominoBrick>();

	public MockPlayer(DominoServiceMock dominoServiceMock) {
		this.dominoServiceMock = dominoServiceMock;
	}

	@Override
	public void run() {
		try {
			if (firstTime) {
				dominoServiceMock.listDominosOnPlayerHand();
			} else {
				dominoServiceMock.drawDominoBrick();
			}
			Thread.sleep(1000);

			DominoBrick dominoToPlay = findElegibleDominoOnHandBrick();
			boolean finded = false;
			while (!finded) {
				DominoBrick drawDominoBrick = dominoServiceMock.drawDominoBrick();
				if (drawDominoBrick == null) {
					finded = true;
				} else {
					if (checkElegibleDominoBrick(drawDominoBrick) != null) {
						dominoToPlay = drawDominoBrick;
					}
				}
			}

			dominoServiceMock.playDominoBrick(dominoToPlay, this);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public Boolean checkElegibleDominoBrick(DominoBrick dominoBrick) {
		List<DominoBrick> dominosOnTable = dominoServiceMock.listDominosOnTable();
		if (dominosOnTable.size() == 0) {
			return true;
		}

		DominoBrick dominoBrickOnLeft = dominosOnTable.get(0);
		DominoBrick dominoBrickOnRigth = dominosOnTable.get(dominosOnTable.size() - 1);

		if (dominoBrickOnLeft.verifyCompatibility(dominoBrick, Side.LEFT) || dominoBrickOnRigth.verifyCompatibility(dominoBrick, Side.LEFT)) {
			return true;
		}
		return false;
	}

	public DominoBrick findElegibleDominoOnHandBrick() {
		for (DominoBrick dominoBrick : dominoBricksOnHand) {
			if (checkElegibleDominoBrick(dominoBrick) != null) {
				return dominoBrick;
			}
		}
		return null;
	}
}
