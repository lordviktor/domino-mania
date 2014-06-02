package br.com.liviazilberberg.dominomania.client.objects;

import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class Domino extends BaseObject {

	public static final Point SIZE = new Point(9, 3);

	public final String[] texture = { "┌───┬───┐", "│ L │ R │", "└───┴───┘" };

	private int rigthSide;
	private int leftSide;

	public Domino(Point position, int leftSide, int rigthSide) {
		super(position, SIZE, new String[0]);

		this.leftSide = leftSide;
		this.rigthSide = rigthSide;

		texture[1] = texture[1].replace("L", String.valueOf(leftSide));
		texture[1] = texture[1].replace("R", String.valueOf(rigthSide));
	}

	@Override
	public String[] getTexture() {
		return this.texture;
	}

	public int getRigthSide() {
		return rigthSide;
	}

	public void setRigthSide(int rigthSide) {
		this.rigthSide = rigthSide;
	}

	public int getLeftSide() {
		return leftSide;
	}

	public void setLeftSide(int leftSide) {
		this.leftSide = leftSide;
	}
}
