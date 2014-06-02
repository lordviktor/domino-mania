package br.com.liviazilberberg.dominomania.client.view.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Observer;

import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.base.BaseModel;
import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;

public abstract class BaseView<Model extends BaseModel, Controller extends BaseController<Model>> implements Observer {

	private List<BaseObject> objectsOnScreen = new ArrayList<BaseObject>();
	private Model model;
	private Controller controller;

	public BaseView(Model baseModel, Controller baseController) {
		this.model = baseModel;
		this.controller = baseController;

		this.model.addObserver(this);

		this.initialize();
	}

	protected abstract void initialize();

	protected Model getModel() {
		return model;
	}

	public Controller getController() {
		return controller;
	}

	private List<BaseObject> objectsToAdd = new ArrayList<BaseObject>();

	public void addObjectToView(BaseObject object) {
		objectsToAdd.add(object);
	}

	private List<BaseObject> objectsToRemove = new ArrayList<BaseObject>();

	public void removeObjectOnView(BaseObject object) {
		objectsToRemove.add(object);
	}

	public void draw(ConsoleOutput output) {
		Iterator<BaseObject> iterator = this.objectsOnScreen.iterator();
		while (iterator.hasNext()) {
			iterator.next().draw(output);
		}
	}

	public void update() {
		Iterator<BaseObject> iterator = this.objectsOnScreen.iterator();
		while (iterator.hasNext()) {
			iterator.next().update();
		}
	}

	public void updateObjects() {
		for (BaseObject object : this.objectsToAdd) {
			this.objectsOnScreen.add(object);
		}

		for (BaseObject object : this.objectsToRemove) {
			this.objectsOnScreen.remove(object);
		}
		this.objectsToAdd = new ArrayList<BaseObject>();
		this.objectsToRemove = new ArrayList<BaseObject>();
	}

}
