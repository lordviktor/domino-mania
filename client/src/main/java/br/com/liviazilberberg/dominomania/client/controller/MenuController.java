package br.com.liviazilberberg.dominomania.client.controller;

import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.GamePlayModel;
import br.com.liviazilberberg.dominomania.client.model.MenuModel;
import br.com.liviazilberberg.dominomania.client.model.Player;
import br.com.liviazilberberg.dominomania.client.model.ProtocolEnum;
import br.com.liviazilberberg.dominomania.client.model.MenuModel.MenuStepEnum;
import br.com.liviazilberberg.dominomania.client.util.GamePadEnum;
import br.com.liviazilberberg.dominomania.client.util.ViewManager;
import br.com.liviazilberberg.dominomania.client.util.InputManager.GamepadActionEvent;
import br.com.liviazilberberg.dominomania.client.view.GamePlayView;

public class MenuController extends BaseController<MenuModel> {

	private MenuModel menuModel;

	public MenuController(MenuModel menuModel) {
		super(menuModel);
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

			if (event.getGamepadAction() == GamePadEnum.A) {
				getModel().setCurrentStep(MenuStepEnum.SETTING_NAME);
			}
		} else if (menuModel.getCurrentStep() == MenuStepEnum.SETTING_NAME) {
			if (event.getGamepadAction() == GamePadEnum.A) {
				navigateToGameplayView();
			}
		}
	}

	private void navigateToGameplayView() {
		GamePlayModel gameplayModel = new GamePlayModel();
		Player player = new Player();
		player.setNickname("Livia");
		GamePlayController baseController = new GamePlayController(gameplayModel, player);
		ViewManager.getInstance().navigateTo(new GamePlayView(gameplayModel, baseController));
	}
}
