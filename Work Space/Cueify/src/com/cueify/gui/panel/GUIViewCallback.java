package com.cueify.gui.panel;

public interface GUIViewCallback {
	/**
	 * This is where any additional setup of the view should take place.
	 * It ensures that the controller and model have access to the full view.
	 */
	public void fxmlLoaded();
}
