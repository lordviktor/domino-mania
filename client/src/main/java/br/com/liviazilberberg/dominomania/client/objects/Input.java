package br.com.liviazilberberg.dominomania.client.objects;

import br.com.liviazilberberg.dominomania.client.objects.Label.TextAlign;
import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class Input extends BaseObject {

	private static final String[] TEXTURE = new String[0];

	private String text = new String();
	private Border border;
	private Label label;

	public Input(Point position, int width) {
		super(position, new Point(width, 3), TEXTURE);
		
		border = new Border(getPosition(), getSize());

		Point labelPosition = Point.sum(getPosition(), 1);
		Point labelSize = Point.sum(getSize(), -2);
		label = new Label(labelPosition, labelSize, text, TextAlign.LEFT);
	}
		
	@Override
	public void draw(ConsoleOutput consoleOutput) {
		label.draw(consoleOutput);
		border.draw(consoleOutput);
	}
}
