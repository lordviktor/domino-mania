package br.com.liviazilberberg.dominomania.client.view;

import java.util.Observable;
import java.util.Observer;

import br.com.liviazilberberg.dominomania.client.controller.MenuController;
import br.com.liviazilberberg.dominomania.client.controller.base.BaseController;
import br.com.liviazilberberg.dominomania.client.model.MenuModel;
import br.com.liviazilberberg.dominomania.client.model.ProtocolEnum;
import br.com.liviazilberberg.dominomania.client.objects.Border;
import br.com.liviazilberberg.dominomania.client.objects.Label;
import br.com.liviazilberberg.dominomania.client.objects.Label.TextAlign;
import br.com.liviazilberberg.dominomania.client.util.InputManager;
import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.view.base.BaseView;

public class MenuView extends BaseView<MenuModel, BaseController> implements
		Observer {

	private Label titulo;
	private Label subtitulo;
	private Label labelTcp;
	private Label labelUdp;
	private Point tcpBorderPosition;
	private Point udpBorderPosition;
	private Border protocolSelectedBorder;

	public MenuView(MenuModel menuModel, MenuController menuController) {
		super(menuModel, menuController);

	}

	@Override
	protected void initialize() {
		Border border = new Border(Point.getOrigin(), new Point(150, 40));
		super.addObjectToView(border);

		titulo = new Label(new Point(1, 10), new Point(148, 1),
				"Bem vindo ao Domino Mania!!!!", TextAlign.CENTER);
		super.addObjectToView(titulo);

		subtitulo = new Label(new Point(1, 13), new Point(148, 1),
				"Selecione o protocolo:", TextAlign.CENTER);
		super.addObjectToView(subtitulo);

		tcpBorderPosition = new Point(75, 19);
		udpBorderPosition = new Point(69, 19);
		if (getModel().getSelectedProtocol() == ProtocolEnum.TCP) {
			protocolSelectedBorder = new Border(tcpBorderPosition, new Point(5,
					3));
		} else {
			protocolSelectedBorder = new Border(udpBorderPosition, new Point(5,
					3));
		}
		super.addObjectToView(protocolSelectedBorder);

		labelTcp = new Label(new Point(76, 20), new Point(3, 1), "TCP",
				TextAlign.CENTER);
		super.addObjectToView(labelTcp);

		labelUdp = new Label(new Point(70, 20), new Point(3, 1), "UDP",
				TextAlign.CENTER);
		super.addObjectToView(labelUdp);

		InputManager.getInstance().addActionListener(this.getController());
	}

	@Override
	public void update(Observable o, Object arg) {
		if (getModel().getSelectedProtocol() == ProtocolEnum.TCP) {
			this.protocolSelectedBorder.setPosition(tcpBorderPosition);
		} else {
			this.protocolSelectedBorder.setPosition(udpBorderPosition);
		}
	}
}
