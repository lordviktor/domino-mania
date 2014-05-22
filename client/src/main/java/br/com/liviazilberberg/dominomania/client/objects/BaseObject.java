package br.com.liviazilberberg.dominomania.client.objects;

import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class BaseObject {

	private Point position;
	private Point size;
	private String[] texture;

	public BaseObject(Point position, Point size, String[] texture) {
		super();
		this.position = position;
		this.size = size;
		this.texture = texture;
	}

	int i = 0;
	
	public void update() {
		i += 1;
		if(i % 3 == 0) {
			this.position.setX(position.getX() + 1);
			this.position.setY(position.getY() + 1);			
		}
	}

	public void draw(ConsoleOutput consoleOutput) {
		consoleOutput.write(this);
	}

	public Point getPosition() {
		return position;
	}

	public void setPosition(Point position) {
		this.position = position;
	}

	public Point getSize() {
		return size;
	}

	public void setSize(Point size) {
		this.size = size;
	}

	public String[] getTexture() {
		return texture;
	}

	public void setTexture(String[] texture) {
		this.texture = texture;
	}
}
