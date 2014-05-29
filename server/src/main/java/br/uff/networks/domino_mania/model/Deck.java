package br.uff.networks.domino_mania.model;

import java.util.ArrayList;
import java.util.Random;

class Deck {

	private final ArrayList<Tile> tiles;
	private final Random random;

	Deck() {
		random = new Random();
		tiles = new ArrayList<>();
		fill();
	}

	private void fill() {
		for (int i = 6; i >= 0; i--)
			for (int j = i; j >= 0; j--)
				tiles.add(new Tile(i, j));
	}

	protected void printDeck() {
		System.out.print("Deck : ");
		int i = 0;
		for (Tile hand2 : tiles) {
			if (hand2 != null) {
				System.out.print("|" + hand2.getLeft() + "|" + hand2.getRight()
						+ "|" + "  ");
				i++;
			}
		}
		System.out.println("");
		System.out.println(" " + i + " Cartas");
	}

	boolean isEmpty() {
		return tiles.size() > 0;
	}

	Tile draw() {
		int index = random.nextInt(tiles.size());
		return tiles.remove(index);
	}

	Tile buyInitialCards() {
		boolean bought = true;
		int i;
		Tile temp = null;
		while (bought) {
			i = (int) (Math.random() * 100);
			if (i < 28) {
				if (tiles.get(i) != null) {
					temp = tiles.get(i);
					tiles.set(i, null);
					bought = false;
				}
			}
		}
		return temp;
	}
}
