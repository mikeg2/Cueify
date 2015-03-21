package com.cueify.gui.panel;

import javafx.scene.Node;

public interface ViewModelDeleget<ModelType, ViewType extends Node> {
	public GUIViewModel<ModelType, ViewType> createViewModel();
}
