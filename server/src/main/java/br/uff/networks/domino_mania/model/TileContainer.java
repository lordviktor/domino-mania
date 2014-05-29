package br.uff.networks.domino_mania.model;

import java.util.LinkedList;

@SuppressWarnings("serial")
public class TileContainer extends LinkedList<Tile> implements JSONSerializable {

	@Override
	public String toJSON() {
		return null;
	}

	@Override
	public void fromJSON(String json) {
	}

}
