package com.cueify.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;

import com.cueify.output.AudioOutput;

/**
 * Contains tests for AudioOutput and supporting classes (AudioOutputSettings, AudioPort)
 * @author Mike
 *
 */
public class AudioOutputTest {
	static String AUDIO_URL_TO_TEST =  "file:///Users/Mike/Desktop/cs.mp3";
	
	public static void main(String[] args) {
		performAllTests();
	}

	public static void performAllTests() {
		testBasicPlaySound();
	}
	
	public static void testBasicPlaySound() {
		JFXPanel fxPanel = new JFXPanel();
		final AudioOutput toTest = new AudioOutput();
		setBasicSettings((AudioOutput)toTest);
		File fileFromURL;
		try {
			fileFromURL = new File(new URI(AUDIO_URL_TO_TEST));
			toTest.setSource(fileFromURL);
			toTest.play();
			toTest.ready().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable,
						Boolean oldValue, Boolean newValue) {
//					if(newValue.booleanValue() == true) {
//						System.out.println("PLAYING STARTED: ");
						toTest.play();
//					}
				}
			});
			System.out.println(toTest.getAbsoluteDuration().getTime());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}
	
	public static void setBasicSettings(AudioOutput settings) {
//		settings.setRate(.5);
//		settings.setVolume(.5);
//		settings.setStartTime(new TimeElapsed(TimeUnit.MINUTE, 1d));
//		settings.setStopTime(new TimeElapsed(TimeUnit.MINUTE, 1.2));
	}
}
