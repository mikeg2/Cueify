package com.cueify.gui.panel;

import javafx.scene.Node;

public abstract class BasicGUIViewModel<T, I extends Node> implements GUIViewModel<T, I> {
	private T model;

	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setModel(T model) {
		this.model = model;
	}

}
