package br.com.liviazilberberg.dominomania.client.service;

import java.util.ArrayList;
import java.util.List;

import br.com.liviazilberberg.dominomania.client.model.base.DominoBrick;

public class DominoService {

	public List<DominoBrick> listDominosOnPlayerHand(){
		List<DominoBrick> result = new ArrayList<DominoBrick>();
		result.add(new DominoBrick(1,2));
		result.add(new DominoBrick(1,3));
		result.add(new DominoBrick(3,2));
		result.add(new DominoBrick(4,6));
		result.add(new DominoBrick(3,3));
		result.add(new DominoBrick(5,4));
		result.add(new DominoBrick(6,2));
		
		return result;
		
	}
}
