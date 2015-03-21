package com.cueify.gui.controls.filemanager;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.FileChooserBuilder;
import javafx.stage.FileChooser.ExtensionFilter;

public class FileChooserButton extends HBox {
	@FXML Button choose_button;
	@FXML Label display_label;
	ObjectProperty<File> fileProperty = new SimpleObjectProperty<File>(null);
	ArrayList<ExtensionFilter> filters = new ArrayList<FileChooser.ExtensionFilter>();
	
	public final String LABEL_DEFAULT_TEXT = "Choose a file";

	public void setFile(File f) {
		fileProperty.set(f);
	}

	public File getFile() {
		return fileProperty.get();
	}
	
	public ObjectProperty<File> file() {
		return fileProperty;
	}

	public FileChooserButton() {
		loadFXML();
		addDefaultValues();
		addActionToButton();
		linkLabelToProperty();
	}

	private void addDefaultValues() {
		display_label.setText(LABEL_DEFAULT_TEXT);
	}

	private void loadFXML() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("FileChooserButtonFXML.fxml"));
	    fxmlLoader.setRoot(this);
	    fxmlLoader.setController(this);
	
	    try {
	        fxmlLoader.load();
	    } catch (IOException exception) {
	        throw new RuntimeException(exception);
	    }		
	}

	public void setExtensions(String...extensions) {
		addExtensionsToFilter(extensions);
	}

	private void addExtensionsToFilter(String[] extensions) {
		ExtensionFilter filter = new ExtensionFilter("Valid", extensions);
		filters.add(filter);
	}

	private void addActionToButton() {
		choose_button.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				launchFileChooser();
			}
		});
	}

	protected void launchFileChooser() {
		File f = getFileFromWindow();
		if (f != null)
			setFile(f);
	}

	private File getFileFromWindow() {
		FileChooser fChoose = FileChooserBuilder.create().extensionFilters().build();
		return fChoose.showOpenDialog(null);
	}

	private void linkLabelToProperty() {
		fileProperty.addListener(new ChangeListener<File>() {

			@Override
			public void changed(ObservableValue<? extends File> observable,
					File oldValue, File newValue) {
				if (newValue == null) {
					display_label.setText(LABEL_DEFAULT_TEXT);
				} else {
					display_label.setText(newValue.getName());
				}
			}
		});
	}

}
