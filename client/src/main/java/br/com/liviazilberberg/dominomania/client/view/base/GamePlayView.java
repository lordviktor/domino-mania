package br.com.liviazilberberg.dominomania.client.view.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import br.com.liviazilberberg.dominomania.client.controller.GamePlayController;
import br.com.liviazilberberg.dominomania.client.model.base.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.objects.Domino;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class GamePlayView extends BaseView<GamePlayModel, GamePlayController> {
	List<Domino> dominoOnScreen;

	public GamePlayView(GamePlayModel baseModel,
			GamePlayController baseController) {
		super(baseModel, baseController);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Observable o, Object arg) {

	}

	@Override
	protected void initialize() {
		this.dominoOnScreen = new ArrayList<Domino>();
		for (int i = 0; i < getModel().getDominoBrickOnHand().size(); i++) {
			Domino dom = new Domino(new Point((i * 9) + 5, 35));
			dominoOnScreen.add(dom);
			this.addObjectToView(dom);
		}
	}

}
