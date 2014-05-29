package br.com.liviazilberberg.dominomania.client.service;

import java.util.ArrayList;
import java.util.List;

import br.com.liviazilberberg.dominomania.client.model.DominoBrick;

public class DominoService {

	private ArrayList<DominoBrick> dominoBricks;

	public DominoService() {
		this.dominoBricks = new ArrayList<DominoBrick>();

		for (int leftSide = 0; leftSide < 7; leftSide++) {
			for (int RigthSide = 0; RigthSide < 7; RigthSide++) {
				this.dominoBricks.add(new DominoBrick(leftSide, RigthSide));
			}
		}
	}

	public List<DominoBrick> listDominosOnPlayerHand() {
		List<DominoBrick> result = this.dominoBricks.subList(0, 6);
		return result;
	}
}
