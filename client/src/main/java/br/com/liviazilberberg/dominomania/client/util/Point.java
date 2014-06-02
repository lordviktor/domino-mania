package br.com.liviazilberberg.dominomania.client.util;

public class Point {

    private int x;
    private int y;
    
    public Point(int x, int y) {
	super();
	
	this.x = x;
	this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

	@Override
	public String toString() {
		return "Point [x=" + x + ", y=" + y + "]";
	}

	public static Point getOrigin() {
		return new Point(0, 0);
	}
	
	public static Point sum(Point point, int scalar) {
		return new Point(point.getX() + scalar, point.getY() + scalar);
	}
}
