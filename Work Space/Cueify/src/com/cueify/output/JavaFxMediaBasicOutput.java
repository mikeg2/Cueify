package com.cueify.output;

import java.io.File;

import javafx.beans.property.SimpleObjectProperty;

import com.cueify.output.helper.MediaPropertyWrapper;

public abstract class JavaFxMediaBasicOutput extends BasicOutput {
	public MediaPropertyWrapper mediaPropertyWrapper = new MediaPropertyWrapper();
	
	@Override
	public void setSource(File file) {
		mediaPropertyWrapper.setPlayerMediaFile(file);
	}

	@Override
	public File getSource() {
		return mediaPropertyWrapper.getPlayerMediaFile();
	}

	@Override
	public SimpleObjectProperty<File> source() {
		return mediaPropertyWrapper.playerMediaFile();
	}

}
