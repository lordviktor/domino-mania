package br.com.liviazilberberg.dominomania.client.objects;

import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class Domino extends BaseObject {

	public static final Point SIZE = new Point(9, 3); 
	
	public final String[] texture = {
		"┌───┬───┐",
		"│ L │ R │",
		"└───┴───┘" 
	};

	public Domino(Point position, int leftSide, int rigthSide) {
		super(position, SIZE, new String[0]);

		texture[1] = texture[1].replace("L", String.valueOf(leftSide));
		texture[1] = texture[1].replace("R", String.valueOf(rigthSide));
	}

	@Override
	public String[] getTexture() {
		return this.texture;
	}
}
