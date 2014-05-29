package br.uff.networks.domino_mania.model;

import br.uff.networks.Transport;

public class PlayRight extends PlayerMovement{

	public PlayRight(Match match) {
		super(match);
	}

	@Override
	public void execute(String message, Transport connection) {
		String tileJSON = connection.receive();
		Tile tile = new Tile();
		tile.fromJSON(tileJSON);
		Player player = match.getCurrentPlayer();
		if(table.addRight(tile))
			player.getHand().remove(tile);
		else
			sendError(player.getTransport());
	}
}
