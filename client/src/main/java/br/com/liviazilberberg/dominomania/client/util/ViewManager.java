package br.com.liviazilberberg.dominomania.client.util;

import br.com.liviazilberberg.dominomania.client.view.base.BaseView;

public class ViewManager {

	private BaseView<?, ?> view;

	public BaseView<?, ?> getCurrentView() {
		return this.view;
	}

	public void navigateTo(BaseView<?, ?> view) {
		this.view = view;
	}

	private ViewManager() {

	}

	private static ViewManager viewManager;

	public static ViewManager getInstance() {
		if (viewManager == null) {
			viewManager = new ViewManager();
		}
		return viewManager;
	}
}
