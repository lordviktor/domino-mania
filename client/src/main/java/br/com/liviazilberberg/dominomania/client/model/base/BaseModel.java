package br.com.liviazilberberg.dominomania.client.model.base;

import java.util.Observable;

public class BaseModel extends Observable {
	
	protected void notifyAllObservers() {
		super.setChanged();
		super.notifyObservers();
	}

}
