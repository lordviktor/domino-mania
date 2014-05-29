package br.com.liviazilberberg.dominomania.client.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	
	public DominoBrick drawDominoBrick(){
		int sortedBrick = new Random().nextInt(dominoBricks.size());
		DominoBrick result = dominoBricks.remove(sortedBrick);
		return result;
	}
	

	public List<DominoBrick> listDominosOnPlayerHand() {
		List<DominoBrick> result = new ArrayList<DominoBrick>();
		for(int i = 0; i < 7; i++) {
			result.add(drawDominoBrick());
		}
		
		return result;
	}
}
