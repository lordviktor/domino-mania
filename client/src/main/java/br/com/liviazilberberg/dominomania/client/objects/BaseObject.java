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

    public void update() {

    }

    public void draw(ConsoleOutput consoleOutput) {
	consoleOutput.write(getPosition(), getTexture());
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
