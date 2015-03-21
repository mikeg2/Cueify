package com.cueify.output;

import java.io.File;

import javafx.beans.property.SimpleObjectProperty;

public interface FileBasedOutput extends Output {
	public void setSource(File file);
	public File getSource();
	public SimpleObjectProperty<File> source();
}
