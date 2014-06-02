package br.com.liviazilberberg.dominomania.client.controller;

import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.model.Player;
import br.com.liviazilberberg.dominomania.client.service.DominoService;
import br.com.liviazilberberg.dominomania.client.service.GameTurnListener;
import br.com.liviazilberberg.dominomania.client.service.event.GameTurnEvent;
import br.com.liviazilberberg.dominomania.client.service.event.GameTurnMoveEvent;
import br.com.liviazilberberg.dominomania.client.service.event.GameWinnerEvent;
import br.com.liviazilberberg.dominomania.client.service.mock.DominoServiceMock;
import br.com.liviazilberberg.dominomania.client.util.GamePadEnum;
import br.com.liviazilberberg.dominomania.client.util.InputManager.GamepadActionEvent;

public class GamePlayController extends BaseController<GamePlayModel> implements GameTurnListener {
	DominoService dominoservice;
	private Player me;

	public GamePlayController(GamePlayModel menuModel, Player player) {
		super(menuModel);
		dominoservice = new DominoServiceMock(player);

		this.me = player;

		dominoservice.addGameTurnListener(this);

		dominoservice.ready();

		menuModel.setDominoBrickOnHand(dominoservice.listDominosOnPlayerHand());
	}

	@Override
	public void actionPerformed(GamepadActionEvent event) {
		if (getModel().isMyTurn()) {
			int currentSelection = getModel().getDominoBrickOnHand().indexOf(getModel().getBrickSelected());
			if (event.getGamepadAction() == GamePadEnum.LEFT) {
				if (currentSelection != 0) {
					getModel().setBrickSelected(getModel().getDominoBrickOnHand().get(currentSelection - 1));
				}
			} else if (event.getGamepadAction() == GamePadEnum.RIGTH) {
				if (currentSelection < getModel().getDominoBrickOnHand().size() - 1) {
					getModel().setBrickSelected(getModel().getDominoBrickOnHand().get(currentSelection + 1));
				}
			}

			if (event.getGamepadAction() == GamePadEnum.A) {
				this.dominoservice.playDominoBrick(getModel().getBrickSelected(), this.me);
			}
		}
	}

	@Override
	public void turnBegin(GameTurnEvent event) {
		getModel().setMyTurn(this.me.equals(event.getPlayer()));
		getModel().setPlayerTurn(event.getPlayer());
		if (getModel().isMyTurn()) {
			getModel().setBrickSelected(getModel().getDominoBrickOnHand().get(0));
		}
	}

	@Override
	public void turnEnd(GameTurnMoveEvent event) {

	}

	@Override
	public void gameEnd(GameWinnerEvent event) {

	}
}