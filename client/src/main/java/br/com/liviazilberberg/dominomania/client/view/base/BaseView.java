package br.com.liviazilberberg.dominomania.client.view.base;

import java.util.ArrayList;
import java.util.List;

import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;

public abstract class BaseView {

	List<BaseObject> objectsOnScreen = new ArrayList<BaseObject>();

	public BaseView() {
		this.initialize();
	}

	protected abstract void initialize();

	public void addObjectToView(BaseObject object) {
		objectsOnScreen.add(object);
	}

	public void Draw(ConsoleOutput output) {
		for (BaseObject object : this.objectsOnScreen) {
			object.draw(output);
		}
	}

	public void update() {
		for (BaseObject object : this.objectsOnScreen) {
			object.update();
		}
	}
}
