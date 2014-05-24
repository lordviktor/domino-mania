package br.com.liviazilberberg.dominomania.client.util;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;

public class InputManager implements KeyListener {

	private InputManager() {

	}

	private static InputManager instance;

	public static InputManager getInstance() {
		if (InputManager.instance == null) {
			InputManager.instance = new InputManager();
		}
		return InputManager.instance;
	}

	List<GamePadActionListener> actionListener = new ArrayList<GamePadActionListener>();

	public void addActionListener(GamePadActionListener listener) {
		actionListener.add(listener);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		GamePadEnum gamePadEnum = GamePadEnum.toGamePadEnum(e.getKeyCode());
		if (gamePadEnum != null) {
			for (GamePadActionListener listener : this.actionListener) {
				listener.actionPerformed(new GamepadActionEvent(gamePadEnum));
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}

	public class GamepadActionEvent {

		private GamePadEnum gamePadSelection;

		public GamepadActionEvent(GamePadEnum gamePadSelection) {
			this.gamePadSelection = gamePadSelection;
		}

		public GamePadEnum getGamepadAction() {
			return this.gamePadSelection;
		}
	}

	public interface GamePadActionListener {
		void actionPerformed(GamepadActionEvent event);
	}
}
