package br.com.liviazilberberg.dominomania.client.view;

import br.com.liviazilberberg.dominomania.client.objects.Border;
import br.com.liviazilberberg.dominomania.client.objects.Label;
import br.com.liviazilberberg.dominomania.client.objects.Label.TextAlign;
import br.com.liviazilberberg.dominomania.client.util.Point;
import br.com.liviazilberberg.dominomania.client.view.base.BaseView;

public class MenuView extends BaseView {

	@Override
	protected void initialize() {
		Border border = new Border(Point.getOrigin(), new Point(150, 40));
		super.addObjectToView(border);

		Label titulo = new Label(new Point(1, 10), new Point(148, 1),
				"Bem vindo ao Domino Mania!!!!", TextAlign.CENTER);
		super.addObjectToView(titulo);
		
		Label subtitulo = new Label(new Point(1, 13), new Point(148, 1),
				"Digite abaixo ip do servidor!!!!", TextAlign.CENTER);
		super.addObjectToView(subtitulo);
		
			
	}
}
