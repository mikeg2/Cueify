package com.cueify.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.JFXPanel;

import com.cueify.cue.AudioCue;
import com.cueify.ticker.TimeTicker;
import com.cueify.ticker.TimeTickerToTick;
import com.cueify.time.TimeElapsed;

public class AudioCueTest {
	static String AUDIO_URL_TO_TEST =  "file:///Users/Mike/Desktop/cs.mp3";

	public static void main(String[] args) {
		JFXPanel fxPanel = new JFXPanel();
		performAllTests();
	}

	public static void performAllTests() {
		playBasicAudio2();
	}
	
	private synchronized static void playBasicAudio2() {
		final AudioCue basicTime = new AudioCue();
//		basicTime.getAudioOutput().setStopTime(new TimeElapsed(10000));
		try {
			basicTime.getAudioOutput().setSource(new File(new URI(AUDIO_URL_TO_TEST)));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		basicTime.getCueTimeline().getPreWait().setWaitTime(new TimeElapsed(0));
		basicTime.getCueTimeline().getPostWait().setWaitTime(new TimeElapsed(0));
		TimeTicker ticker = new TimeTicker();
		((TimeTickerToTick)ticker.getToTick()).addTickable(basicTime);
		ticker.startTicking();
		basicTime.getCueTimeline().play();
		basicTime.getAudioOutput().ready().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
//				if(newValue.booleanValue() == true) {
//					System.out.println("PLAYING STARTED: ");
					basicTime.getCueTimeline().play();
//				}
			}
		});
	}
}
