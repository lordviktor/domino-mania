package br.com.liviazilberberg.dominomania.client.model;

import br.com.liviazilberberg.dominomania.client.model.base.BaseModel;

public class MenuModel extends BaseModel {

	private MenuStepEnum currentStep = MenuStepEnum.SELECT_PROTOCOL;

	private ProtocolEnum selectedProtocol = ProtocolEnum.TCP;
	
	public MenuStepEnum getCurrentStep() {
		return currentStep;
	}

	public void setCurrentStep(MenuStepEnum currentStep) {
		this.currentStep = currentStep;
		notifyAllObservers();
	}

	public ProtocolEnum getSelectedProtocol() {
		return selectedProtocol;
	}

	public void setSelectedProtocol(ProtocolEnum selectedProtocol) {
		this.selectedProtocol = selectedProtocol;
		notifyAllObservers();
	}

	public enum MenuStepEnum {
		SELECT_PROTOCOL, SETTING_NAME
	}
	
}
