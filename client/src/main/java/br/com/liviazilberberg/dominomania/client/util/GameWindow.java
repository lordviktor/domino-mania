package br.com.liviazilberberg.dominomania.client.util;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.TextArea;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class GameWindow {

	private Frame frame;
	private TextArea textArea;

	public GameWindow() {

		constructGui();

	}

	private void constructGui() {
		frame = new Frame("Domino Mania");
		frame.setSize(1200, 800);
		frame.addKeyListener(InputManager.getInstance());
		frame.setFocusable(true);
		frame.addWindowListener(new WindowListener() {
			
			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			
			@Override
			public void windowIconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeiconified(WindowEvent e) {
				
			}
			
			@Override
			public void windowDeactivated(WindowEvent e) {
				
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
			@Override
			public void windowClosed(WindowEvent e) {
				
			}
			
			@Override
			public void windowActivated(WindowEvent e) {
				
			}
		});
		
		textArea = new TextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.GREEN);
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
		textArea.setFocusable(false);

		frame.add(textArea);
		frame.setVisible(true);
	}

	public void setText(String text) {
		textArea.setText(text);
	}
}
