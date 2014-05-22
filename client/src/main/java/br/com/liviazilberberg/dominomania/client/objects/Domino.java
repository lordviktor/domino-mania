package br.com.liviazilberberg.dominomania.client.objects;

import br.com.liviazilberberg.dominomania.client.util.Point;

public class Domino extends BaseObject {

	public static final Point SIZE = new Point(9, 3); 
	
	public static final String[] TEXTURE = {
		"┌───┬───┐", 
		"│ 3 │ 3 │", 
		"└───┴───┘" 
	};
	
	public Domino(Point position) {
		super(position, SIZE, TEXTURE);
		
	}
}
