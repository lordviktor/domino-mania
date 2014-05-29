package br.uff.networks.domino_mania.model;

import br.uff.networks.Transport;

public class Player {

	private String name;
	private Transport transport;
	private TileContainer hand;
	private int team;

	public Player(String name, TileContainer hand, Transport transport) {
		this.name = name;
		this.transport = transport;
		hand = new TileContainer();
	}

	public String getName() {
		return name;
	}

	public Transport getTransport() {
		return transport;
	}

	public TileContainer getHand() {
		return hand;
	}
	
	public void setTeam(int team){
		this.team = team;
	}
	
	public int getTeam(){
		return team;
	}
}
