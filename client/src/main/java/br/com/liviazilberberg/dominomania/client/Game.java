package br.com.liviazilberberg.dominomania.client;

import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.InputManager;
import br.com.liviazilberberg.dominomania.client.util.NavigationManager;
import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.view.base.BaseView;

public class Game implements NavigationManager {

	private BaseView currentView;
	private Point size;

	public Game(Point size, BaseView initialView) {
		this.size = size;
		this.currentView = initialView;
	}

	public void navigateTo(BaseView view) {
		this.currentView = view;
	}

	public void execute() {
		while (true) {
			sleep();
			clearConsole();
			ConsoleOutput console = new ConsoleOutput(size);

			this.currentView.update();
			this.currentView.Draw(console);

			System.out.println(console.toString());
		}
	}

	private void sleep() {	
		InputManager inputManager = new InputManager();
		inputManager.readInputForTime(30000);
		//Thread.sleep(33);
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
