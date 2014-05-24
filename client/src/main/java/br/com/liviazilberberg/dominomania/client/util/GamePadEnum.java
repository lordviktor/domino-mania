package br.com.liviazilberberg.dominomania.client.util;

import java.awt.event.KeyEvent;

public enum GamePadEnum {

	LEFT(KeyEvent.VK_LEFT), RIGTH(KeyEvent.VK_RIGHT), UP(KeyEvent.VK_UP), DOWN(
			KeyEvent.VK_DOWN),

	A(KeyEvent.VK_Z), B(KeyEvent.VK_X);

	private int keyCode;

	GamePadEnum(int keyCode) {
		this.keyCode = keyCode;
	}

	public static GamePadEnum toGamePadEnum(int keyCode) {
		if (keyCode == LEFT.keyCode) {
			return LEFT;
		}
		if (keyCode == RIGTH.keyCode) {
			return RIGTH;
		}
		if (keyCode == UP.keyCode) {
			return UP;
		}
		if (keyCode == DOWN.keyCode) {
			return DOWN;
		}
		if (keyCode == A.keyCode) {
			return A;
		}
		if (keyCode == B.keyCode) {
			return B;
		}
		return null;
	}
}
