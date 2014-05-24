package br.com.liviazilberberg.dominomania.client;

import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.GameWindow;
import br.com.liviazilberberg.dominomania.client.util.NavigationManager;
import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.util.ViewManager;
import br.com.liviazilberberg.dominomania.client.view.base.BaseView;

public class Game implements NavigationManager {

	private Point size;
	private GameWindow gameWindow;

	public Game(Point size, BaseView<?, ?> initialView) {
		this.size = size;

		ViewManager.getInstance().navigateTo(initialView);

		this.gameWindow = new GameWindow();
	}

	public void execute() {
		while (true) {
			sleep();

			ConsoleOutput console = new ConsoleOutput(size);

			ViewManager.getInstance().getCurrentView().update();
			ViewManager.getInstance().getCurrentView().draw(console);

			gameWindow.setText(console.toString());
		}
	}

	private void sleep() {
		try {
			Thread.sleep(33);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// something REALLY bad happen
			e.printStackTrace();
		}
	}
}
