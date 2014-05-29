package br.uff.networks.domino_mania.model;

import br.uff.networks.Transport;

public class PlayLeft extends PlayerMovement{

	public PlayLeft(Match match) {
		super(match);
	}

	@Override
	public void execute(String message, Transport connection) {
		String tileJSON = connection.receive();
		Tile tile = new Tile();
		tile.fromJSON(tileJSON);
		Player player = match.getCurrentPlayer();
		if(table.addLeft(tile))
			player.getHand().remove(tile);
		else
			sendError(player.getTransport());
	}
}