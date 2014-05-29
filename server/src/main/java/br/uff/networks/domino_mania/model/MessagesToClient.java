package br.uff.networks.domino_mania.model;

public class MessagesToClient {

	public static final String YOUR_TURN = "turn\nyou";
	public static final String INVALID_MOVEMENT = "invalid movement";

	public static String turn(String name) {
		return "turn\n" + name;
	}

	public static String gameOver(String name) {
		return "game over\n" + name;
	}
}
