package com.cueify.output.helper;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;

@XmlAccessorType(XmlAccessType.NONE)
public class MediaPropertyWrapper {
	@XmlTransient
	private SimpleObjectProperty<Media> playerMedia = new SimpleObjectProperty<>();
	
	@XmlTransient
	private SimpleObjectProperty<File> playerMediaFile = new SimpleObjectProperty<>();

	public MediaPropertyWrapper() {
		linkPlayerMediaToURI();
	}

	private void linkPlayerMediaToURI() {
		playerMediaFile.addListener(new ChangeListener<File>() {
					@Override
					public void changed(
							ObservableValue<? extends File> observable,
							File oldValue, File newValue) {
						playerMedia.setValue(new Media(newValue.getAbsoluteFile().toURI().toString()));
		}});
		playerMedia.addListener(new ChangeListener<Media>() {
			@Override
			public void changed(ObservableValue<? extends Media> observable,
					Media oldValue, Media newValue) {
					try {
						playerMediaFile.setValue(new File(new URI(newValue.getSource())));
					} catch (URISyntaxException e) {
						e.printStackTrace();
					}
		}});
	}

	public SimpleObjectProperty<File> playerMediaFile() {
		return playerMediaFile;
	}
	
	public File getPlayerMediaFile() {
		return playerMediaFile.get();
	}
	
	public void setPlayerMediaFile(File file) {
		playerMediaFile.set(file);
	}
	
	public Media getPlayerMedia() {
		return playerMedia.get();
	}
	
	public SimpleObjectProperty<Media> media() {
		return playerMedia;
	}
	
	public void setPlayerMedia(Media media) {
		playerMedia.set(media);
	}
}
