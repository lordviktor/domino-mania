package br.com.liviazilberberg.dominomania.client.objects;

import org.apache.commons.lang3.StringUtils;

import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.Point;

public class Label extends BaseObject {

	public static final String[] TEXTURE = new String[0];

	private TextAlign align;

	private String text;

	public Label(Point position, Point size, String text, TextAlign align) {
		super(position, size, TEXTURE);
		this.align = align;
		this.text = text;
	}

	@Override
	public String[] getTexture() {
		return this.align.generateAlignedText(text, getSize());
	}

	public enum TextAlign {
		LEFT {
			@Override
			public String[] generateAlignedText(String text, Point size) {
				if (limit(text, size) != null) {
					return limit(text, size);
				}
				String result = "";
				result += text;
				result += StringUtils.repeat(" ", size.getX() - text.length());
				return new String[] { result };
			}
		},
		RIGTH {
			@Override
			public String[] generateAlignedText(String text, Point size) {
				if (limit(text, size) != null) {
					return limit(text, size);
				}
				String result = "";
				result += StringUtils.repeat(" ", size.getX() - text.length());
				result += text;
				return new String[] { result };
			}
		},
		CENTER {
			@Override
			public String[] generateAlignedText(String text, Point size) {
				if (limit(text, size) != null) {
					return limit(text, size);
				}
				String result = "";
				int border = (size.getX() - text.length()) / 2;

				result += StringUtils.repeat(' ', border);
				result += text;
				result += StringUtils.repeat(' ', border);
				if (result.length() != size.getX()) { // caso da divisao acima
														// causar arredondamento
														// para baixo ;)
					result += " ";
				}
				return new String[] { result };
			}
		};

		protected String[] limit(String text, Point size) {
			if (size.getX() < text.length()) {
				return new String[] { text.substring(0, size.getX()) };
			} else if (size.getX() == text.length()) {
				return new String[] { text };
			}
			return null;
		}

		public abstract String[] generateAlignedText(String text, Point size);
	}
}
