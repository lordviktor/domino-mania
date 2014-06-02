package br.com.liviazilberberg.dominomania.client.service;

import br.com.liviazilberberg.dominomania.client.service.event.GameTurnEvent;
import br.com.liviazilberberg.dominomania.client.service.event.GameTurnMoveEvent;
import br.com.liviazilberberg.dominomania.client.service.event.GameWinnerEvent;

public interface GameTurnListener {

	void turnBegin(GameTurnEvent event);

	void turnEnd(GameTurnMoveEvent event);
	
	void gameEnd(GameWinnerEvent event);
}
