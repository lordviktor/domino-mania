package br.com.liviazilberberg.dominomania.client.objects;

import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class Border extends BaseObject {

	public static final String[] TEXTURE = { "╔═╗", "║ ║", "╚═╝" };

	public Border(Point position, Point size) {
		super(position, size, TEXTURE);
	}

	@Override
	public String[] getTexture() {
		if (getSize().getX() == 0 || getSize().getY() == 0) {
			return new String[0];
		}
		String[] result = new String[getSize().getY()];

		for (int line = 0; line < getSize().getY(); line++) {
			String lineContent = new String();
			String textureToUse;
			if (line == 0) {
				textureToUse = TEXTURE[0];
			} else if (line == getSize().getY() - 1) {
				textureToUse = TEXTURE[2];
			} else {
				textureToUse = TEXTURE[1];
			}
			for (int column = 0; column < getSize().getX(); column++) {
				if(column == 0) {
					lineContent += textureToUse.substring(0, 1);
				} else if(column == getSize().getX() - 1) {
					lineContent += textureToUse.substring(2, 3);
				} else {
					lineContent += textureToUse.substring(1, 2);
				}
			}
			result[line] = lineContent;
		}
		
		return result;
	}
}
