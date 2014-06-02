domino-mania
============


Olá Galera. Como a Livia alinhou com vocês, estamos a todo vapor no desenvolvimento do front-end, acredito que conseguiremos respeitar o prazo acordado.

Conforme foi alinhado com a Livia, sobre como proceder com a integração entre cliente e servidor, vamos seguir com a implementação necessário. Nela eu compreendo todas as tomadas de decisão do usuário e questionamento que o jogo precisa fazer para o mesmo. Porém ainda remanesce a dúvida de como desenvolver a comunição entre o cliente e o servidor, já que existe um protocolo com mensagens e operações que infelizmente desconheço. Partindo daí sobre uma Interface `DominoService`.

Essa interface segue abaixo:
```(java)

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

```
