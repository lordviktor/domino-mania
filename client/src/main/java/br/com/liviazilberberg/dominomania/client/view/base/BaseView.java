package br.com.liviazilberberg.dominomania.client.view.base;

import java.util.ArrayList;
import java.util.List;
import java.util.Observer;

import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.base.BaseModel;
import br.com.liviazilberberg.dominomania.client.objects.base.BaseObject;
import br.com.liviazilberberg.dominomania.client.util.ConsoleOutput;

public abstract class BaseView<Model extends BaseModel, Controller extends BaseController> implements Observer {

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

	public void addObjectToView(BaseObject object) {
		objectsOnScreen.add(object);
	}

	public void draw(ConsoleOutput output) {
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
