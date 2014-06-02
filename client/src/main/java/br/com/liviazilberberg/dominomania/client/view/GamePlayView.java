package br.com.liviazilberberg.dominomania.client.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import br.com.liviazilberberg.dominomania.client.controller.GamePlayController;
import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.objects.Border;
import br.com.liviazilberberg.dominomania.client.objects.Domino;
import br.com.liviazilberberg.dominomania.client.objects.Label;
import br.com.liviazilberberg.dominomania.client.objects.Label.TextAlign;
import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.view.base.BaseView;

public class GamePlayView extends BaseView<GamePlayModel, GamePlayController> {
	private List<Domino> dominoOnHand;
	private List<Domino> dominoOnScreen;
	private Border border;
	private Label labelCurrentPlayer;
	private Border dominoBrickSelection;

	public GamePlayView(GamePlayModel baseModel, GamePlayController baseController) {
		super(baseModel, baseController);

	}

	@Override
	public void update(Observable o, Object arg) {
	}

	@Override
	public void update() {
		super.update();

		if (getModel().getPlayerTurn() != null) { // atualiza label de jogador
													// da vez a partir do event
													// GameTurnEvent
			if (getModel().isMyTurn()) {
				labelCurrentPlayer.setText("Minha Vez!");
			} else {
				labelCurrentPlayer.setText("Vez do jogador: " + getModel().getPlayerTurn().getNickname());
			}
		}

		if (getModel().getBrickSelected() != null) {
			for (Domino domino : this.dominoOnHand) {
				if (getModel().getBrickSelected().equals(domino)) {
					dominoBrickSelection.setPosition(Point.sum(domino.getPosition(), -1));
				}
			}
		}
	}

	@Override
	protected void initialize() {
		this.dominoOnHand = new ArrayList<Domino>();
		int order = 0;

		border = new Border(new Point(0, 0), new Point(150, 40));
		super.addObjectToView(border);

		dominoBrickSelection = new Border(new Point(4, 34), new Point(11, 5));
		super.addObjectToView(dominoBrickSelection);

		for (DominoBrick dominoBrick : getModel().getDominoBrickOnHand()) {
			Point position = new Point(order * 10 + 5, 35);

			Domino domino = new Domino(position, dominoBrick.getLeftSideNumber(), dominoBrick.getRigthSideNumber());

			this.dominoOnHand.add(domino);
			super.addObjectToView(domino);
			order++;
		}

		labelCurrentPlayer = new Label(new Point(1, 4), new Point(148, 1), "", TextAlign.CENTER);
		super.addObjectToView(labelCurrentPlayer);
	}
}
