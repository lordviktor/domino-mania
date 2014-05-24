package br.com.liviazilberberg.dominomania.client.controller;

import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.base.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.service.DominoService;

public class GamePlayController extends BaseController<GamePlayModel> {
	DominoService dominoservice = new DominoService();

	public GamePlayController(GamePlayModel menuModel) {
		super(menuModel);
		menuModel.setDominoBrickOnHand(dominoservice.listDominosOnPlayerHand());
	}
}