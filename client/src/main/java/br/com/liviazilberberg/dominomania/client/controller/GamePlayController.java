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

public class GamePlayController extends BaseController<GamePlayModel> implements GameTurnListener {
	DominoService dominoservice;

	public GamePlayController(GamePlayModel menuModel, Player player) {
		super(menuModel);
		dominoservice = new DominoServiceMock(player);

		dominoservice.addGameTurnListener(this);

		dominoservice.ready();

		menuModel.setDominoBrickOnHand(dominoservice.listDominosOnPlayerHand());
	}

	
	@Override
	public void turnBegin(GameTurnEvent event) {

	}

	@Override
	public void turnEnd(GameTurnMoveEvent event) {

	}

	@Override
	public void gameEnd(GameWinnerEvent event) {

	}
}