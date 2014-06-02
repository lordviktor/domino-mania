package br.com.liviazilberberg.dominomania.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import br.com.liviazilberberg.dominomania.client.controller.GamePlayController;
import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.objects.Border;
import br.com.liviazilberberg.dominomania.client.objects.Domino;
import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.view.base.BaseView;

public class GamePlayView extends BaseView<GamePlayModel, GamePlayController> {
	private List<Domino> dominoOnHand;
	private List<Domino> dominoOnScreen;
	private Border border;

	public GamePlayView(GamePlayModel baseModel, GamePlayController baseController) {
		super(baseModel, baseController);

	}

	@Override
	public void update(Observable o, Object arg) {
	
		
		
	}

	@Override
	protected void initialize() {
		this.dominoOnHand = new ArrayList<Domino>();
		int order = 0;
		for (DominoBrick dominoBrick : getModel().getDominoBrickOnHand()) {
			Point position = new Point(order * 9 + 5, 35);

			Domino domino = new Domino(position, dominoBrick.getLeftSideNumber(), dominoBrick.getRigthSideNumber());

			this.dominoOnHand.add(domino);
			super.addObjectToView(domino);
			order++;
		}
		
		border = new Border(new Point(0, 0), new Point(150, 40));
		super.addObjectToView(border);
	}
}
