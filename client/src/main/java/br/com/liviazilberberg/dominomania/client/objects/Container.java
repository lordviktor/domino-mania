package br.com.liviazilberberg.dominomania.client.objects;

import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.Point;


/*
 * Classe para encapsular o desenho de varios objetos 
 */
public class Container extends BaseObject {

	public static final String[] TEXTURE = new String[0];
	
	public Container(Point position, Point size) {
		super(position, size, TEXTURE);

	}

	
}
