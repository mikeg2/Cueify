package com.cueify.gui.panel;

import javafx.scene.Node;

/**
 * 
 * @author Mike
 *
 * @param <T>
 */
public interface GUIViewModel<ModelType, ViewType extends Node> {
	public void linkControllerToView(ViewType panel);
	public ModelType getModel();
	public void setModel(ModelType model);
}
