package br.com.liviazilberberg.dominomania.client.controller.base;

import br.com.liviazilberberg.dominomania.client.model.base.BaseModel;
import br.com.liviazilberberg.dominomania.client.util.InputManager;
import br.com.liviazilberberg.dominomania.client.util.InputManager.GamePadActionListener;
import br.com.liviazilberberg.dominomania.client.util.InputManager.GamepadActionEvent;

public class BaseController<Model extends BaseModel> implements
		GamePadActionListener {
	private Model model;

	public BaseController(Model model) {
		this.model = model;
		InputManager.getInstance().addActionListener(this);
	}

	@Override
	public void actionPerformed(GamepadActionEvent event) {
		// TODO Auto-generated method stub

	}

	protected Model getModel() {
		return model;
	}

}
