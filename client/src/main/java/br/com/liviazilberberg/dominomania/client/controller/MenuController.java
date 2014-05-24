package br.com.liviazilberberg.dominomania.client.controller;

import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.MenuModel;
import br.com.liviazilberberg.dominomania.client.model.ProtocolEnum;
import br.com.liviazilberberg.dominomania.client.model.MenuModel.MenuStepEnum;
import br.com.liviazilberberg.dominomania.client.util.GamePadEnum;
import br.com.liviazilberberg.dominomania.client.util.InputManager.GamepadActionEvent;

public class MenuController extends BaseController {

	private MenuModel menuModel;

	public MenuController(MenuModel menuModel) {
		this.menuModel = menuModel;
	}

	@Override
	public void actionPerformed(GamepadActionEvent event) {
		super.actionPerformed(event);

		if (menuModel.getCurrentStep() == MenuStepEnum.SELECT_PROTOCOL) {
			if (event.getGamepadAction() == GamePadEnum.LEFT) {
				menuModel.setSelectedProtocol(ProtocolEnum.UDP);
			} else if (event.getGamepadAction() == GamePadEnum.RIGTH) {
				menuModel.setSelectedProtocol(ProtocolEnum.TCP);
			}
		}
	}
}