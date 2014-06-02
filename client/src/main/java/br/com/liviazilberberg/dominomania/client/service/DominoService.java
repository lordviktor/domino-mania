package br.com.liviazilberberg.dominomania.client.service;

import java.util.List;

import br.com.liviazilberberg.dominomania.client.model.DominoBrick;
import br.com.liviazilberberg.dominomania.client.model.Player;

public interface DominoService {

	/**
	 * Compra um peca de domino. Quando o jogador nao possui pecas disponiveis.
	 * @return - a nova peça do jogador.
	 */
	DominoBrick drawDominoBrick();

	/**
	 * Lista os dominos na mao do jogado. Tipicamente chamado no inicio da partida.
	 * @return - A lista de pecas de domino na mao do jogador.
	 */
	List<DominoBrick> listDominosOnPlayerHand();
	
	/**
	 * Joga a peca informada para o seguinte jogador
	 */
	void playDominoBrick(DominoBrick dominoBrick, Player player);
	
	/**
	 * Lista as pedras na mesa.
	 * @return - a lista de pedras de domino na mesa.
	 */
	List<DominoBrick> listDominosOnTable();

	/**
	 * Listener que sera notificara a vez de quem é a atual. Esse evento ocorrerá a partir da mensagem via broadcast do socket
	 * @param listener - Classe listener, deve ser chamada no evento acima
	 */
	void addGameTurnListener(GameTurnListener listener);
}