package br.uff.networks.domino_mania.model;

import br.uff.networks.NetworkCommand;
import br.uff.networks.Transport;

abstract class PlayerMovement implements NetworkCommand {
	
	protected final Table table;
	protected final Match match;
	
	public PlayerMovement(Match match) {
		this.match = match;
		table = match.getTable();
	}
	
	protected void sendError(Transport transport){
		transport.send(MessagesToClient.INVALID_MOVEMENT);
	}

}
