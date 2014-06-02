package br.com.liviazilberberg.dominomania.client.model;

import java.util.ArrayList;
import java.util.List;

import br.com.liviazilberberg.dominomania.client.model.base.BaseModel;

public class GamePlayModel extends BaseModel {

	private List<DominoBrick> dominoBrickOnTable = new ArrayList<DominoBrick>();
	private List<DominoBrick> dominoBrickOnHand = new ArrayList<DominoBrick>();
	
	private List<Player> enemies = new ArrayList<Player>();
	
	private boolean isMyTurn = false;
	
	private Player playerTurn = null;
	
	public boolean isMyTurn() {
		return isMyTurn;
	}

	public void setMyTurn(boolean isMyTurn) {
		this.isMyTurn = isMyTurn;
		notifyAllObservers();
	}

	public Player getPlayerTurn() {
		return playerTurn;
	}

	public void setPlayerTurn(Player playerTurn) {
		this.playerTurn = playerTurn;
		notifyAllObservers();
	}

	public List<Player> getEnemies() {
		return enemies;
	}

	public void setEnemies(List<Player> enemies) {
		this.enemies = enemies;
	}

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
