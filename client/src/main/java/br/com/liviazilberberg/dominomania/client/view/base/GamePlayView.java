package br.com.liviazilberberg.dominomania.client.view.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import br.com.liviazilberberg.dominomania.client.controller.GamePlayController;
import br.com.liviazilberberg.dominomania.client.model.base.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.base.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.objects.Domino;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class GamePlayView extends BaseView<GamePlayModel, GamePlayController> {
	List<Domino> dominoOnScreen;

	public GamePlayView(GamePlayModel baseModel,
			GamePlayController baseController) {
		super(baseModel, baseController);

	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	protected void initialize() {
		this.dominoOnScreen = new ArrayList<Domino>();
		int order = 0;
		for (DominoBrick dominoBrick : getModel().getDominoBrickOnHand()) {
			Point position = new Point(order * 9 + 5, 35);
			
			Domino domino = new Domino(position,
					dominoBrick.getLeftSideNumber(),
					dominoBrick.getRigthSideNumber());

			this.dominoOnScreen.add(domino);
			super.addObjectToView(domino);
			order++;
		}
	}
}
