package br.com.liviazilberberg.dominomania.client;

import br.com.liviazilberberg.dominomania.client.controller.MenuController;
import br.com.liviazilberberg.dominomania.client.model.MenuModel;
import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.view.MenuView;

public class Main {

	public static void main(String[] args) {
		MenuModel menuModel = new MenuModel();
		MenuController menuController = new MenuController(menuModel);
		MenuView initialView = new MenuView(menuModel, menuController);
		new Game(new Point(150, 40), initialView).execute();
	}

}
