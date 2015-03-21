package com.cueify.gui.panel;

import java.io.IOException;
import java.net.URL;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

public class GUIPanelLoader {
	public static <ModelType, ViewType extends Node> void loadPanel
	(ViewType root, GUIViewModel<ModelType, ViewType> controller, ModelType model, URL fxmlfile, GUIViewCallback callback) {
		loadFXML(root, fxmlfile);
		if(callback != null)
			callback.fxmlLoaded();
		setUpView(root, controller, model);
	}
	
	private static <ModelType, ViewType extends Node> void setUpView(ViewType root, GUIViewModel<ModelType, ViewType> controller, ModelType model) {
		controller.linkControllerToView(root);
	}

	private static void loadFXML(Object root, URL resource) {
		FXMLLoader fxmlLoader = new FXMLLoader(resource);
	    fxmlLoader.setRoot(root);
	    fxmlLoader.setController(root);
	    try {
	        fxmlLoader.load();
	    } catch (IOException exception) {
	        throw new RuntimeException(exception);
	    }		
	}
}
