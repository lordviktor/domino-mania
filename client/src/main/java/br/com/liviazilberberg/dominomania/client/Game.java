package br.com.liviazilberberg.dominomania.client;

import java.util.ArrayList;
import java.util.List;

import br.com.liviazilberberg.dominomania.client.objects.BaseObject;
import br.com.liviazilberberg.dominomania.client.objects.Domino;
import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class Game {

	List<BaseObject> objectsOnScreen = new ArrayList<BaseObject>();

	public Game() {
		initialize();
	}

	public void initialize() {
		objectsOnScreen.add(new Domino(new Point(3, 3)));
		objectsOnScreen.add(new Domino(new Point(13, 3)));
		objectsOnScreen.add(new Domino(new Point(23, 3)));
		objectsOnScreen.add(new Domino(new Point(33, 3)));
	}

	public void execute() {

		while (true) {
			try {
				Thread.sleep(33);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			clearConsole();
			ConsoleOutput console = new ConsoleOutput(new Point(100, 40));
			for (BaseObject object : objectsOnScreen) {
				object.update();
				object.draw(console);
			}
			System.out.println(console.toString());
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
			// Handle any exceptions.
		}
	}

}
