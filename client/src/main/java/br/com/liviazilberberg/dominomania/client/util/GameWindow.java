package br.com.liviazilberberg.dominomania.client.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;

public class GameWindow {

	private Frame frame;
	private TextArea textArea;

	public GameWindow() {

		constructGui();

	}

	private void constructGui() {
		frame = new Frame("Domino Mania");
		frame.setSize(800, 800);
		frame.addKeyListener(InputManager.getInstance());
		frame.setFocusable(true);

		textArea = new TextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.GREEN);
		textArea.setFont(new Font("Courier", Font.PLAIN, 14));
		textArea.setFocusable(false);

		frame.add(textArea);
		frame.setVisible(true);
	}

	public void setText(String text) {
		textArea.setText(text);
	}
}
