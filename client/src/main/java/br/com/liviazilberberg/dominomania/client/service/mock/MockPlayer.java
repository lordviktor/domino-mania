package br.com.liviazilberberg.dominomania.client.service.mock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.DominoBrick.Side;
import br.com.liviazilberberg.dominomania.client.model.Player;

public class MockPlayer extends Player implements Callable<Void> {

	private DominoServiceMock dominoServiceMock;

	private boolean firstTime = true;

	private List<DominoBrick> dominoBricksOnHand = new ArrayList<DominoBrick>();

	public MockPlayer(DominoServiceMock dominoServiceMock) {
		this.dominoServiceMock = dominoServiceMock;
	}

	@Override
	public Void call() {
		try {
			if (firstTime) {
				dominoBricksOnHand = dominoServiceMock.listDominosOnPlayerHand();
			} else {
				dominoBricksOnHand.add(dominoServiceMock.drawsDominoBrick());
			}
			Thread.sleep(1000);

			DominoBrick dominoToPlay = findElegibleDominoOnHandBrick();
			boolean finded = dominoToPlay != null;
			while (!finded) {
				DominoBrick drawDominoBrick = dominoServiceMock.drawsDominoBrick();
				if (drawDominoBrick == null) {
					finded = true;
				} else {
					dominoBricksOnHand.add(drawDominoBrick);
					if (findElegibleDominoOnHandBrick() != null) {
						dominoToPlay = drawDominoBrick;
						finded = true;
					}
				}
			}
			dominoServiceMock.playDominoBrick(dominoToPlay, this);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
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
