package br.com.liviazilberberg.dominomania.client.controller;

import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.model.Player;
import br.com.liviazilberberg.dominomania.client.service.DominoService;
import br.com.liviazilberberg.dominomania.client.service.GameTurnEvent;
import br.com.liviazilberberg.dominomania.client.service.GameTurnListener;
import br.com.liviazilberberg.dominomania.client.service.mock.DominoServiceMock;

public class GamePlayController extends BaseController<GamePlayModel> implements GameTurnListener {
	DominoService dominoservice;

	public GamePlayController(GamePlayModel menuModel, Player player) {
		super(menuModel);
		dominoservice = new DominoServiceMock(player);
		
		dominoservice.addGameTurnListener(this);
		
		menuModel.setDominoBrickOnHand(dominoservice.listDominosOnPlayerHand());
	}

	@Override
	public void turnOccurred(GameTurnEvent event) {

	}
}