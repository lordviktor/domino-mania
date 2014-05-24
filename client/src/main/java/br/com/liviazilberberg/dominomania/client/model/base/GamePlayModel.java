package br.com.liviazilberberg.dominomania.client.model.base;

import java.util.ArrayList;
import java.util.List;

public class GamePlayModel extends BaseModel {

	private List<DominoBrick> dominoBrickOnTable = new ArrayList<DominoBrick>();
	private List<DominoBrick> dominoBrickOnHand = new ArrayList<DominoBrick>();

	public List<DominoBrick> getDominoBrickOnTable() {
		return dominoBrickOnTable;
	}

	public void setDominoBrickOnTable(List<DominoBrick> dominoBrickOnTable) {
		this.dominoBrickOnTable = dominoBrickOnTable;
		notifyAllObservers();
	}

	public List<DominoBrick> getDominoBrickOnHand() {
		return dominoBrickOnHand;
	}

	public void setDominoBrickOnHand(List<DominoBrick> dominoBrickOnHand) {
		this.dominoBrickOnHand = dominoBrickOnHand;
		notifyAllObservers();
	}

}
