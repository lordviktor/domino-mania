package br.com.liviazilberberg.dominomania.client;

import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.view.MenuView;

public class Main {

	public static void main(String[] args) {
		new Game(new Point(150, 40), new MenuView()).execute();
	}

}
