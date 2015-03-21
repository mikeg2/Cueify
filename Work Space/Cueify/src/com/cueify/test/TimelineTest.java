package com.cueify.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.embed.swing.JFXPanel;

import com.cueify.action.Action;
import com.cueify.output.AudioOutput;
import com.cueify.ticker.TimeTicker;
import com.cueify.ticker.TimeTickerToTick;
import com.cueify.time.TimeElapsed;
import com.cueify.timeline.tickable.TickableTimeline;
import com.cueify.timeline.tickable.cue.CueTimeline;
import com.cueify.timeline.tickable.cue.CueTimelineFactory;

public class TimelineTest {
	static String AUDIO_URL_TO_TEST =  "file:///Users/Mike/Desktop/calb.mp3";

	public static void main(String[] args) {
		performAllTests();
	}

	public static void performAllTests() {
		JFXPanel fxPanel = new JFXPanel();
		//addPrintActionAndPlay();
		testCueTimeline();
	}
  
	private static void testCueTimeline() {
		CueTimeline audioCueTimeline = CueTimelineFactory.getAudioCueTimeline();
		AudioOutput output = (AudioOutput) audioCueTimeline.getContent();
//		output.setStartTime(new TimeElapsed(90000));
		output.setStopTime(new TimeElapsed(100000));
//		output.setRate(1);
		File fileFromURL;
		try {
			fileFromURL = new File(new URI(AUDIO_URL_TO_TEST));
			output.setSource(new File(AUDIO_URL_TO_TEST));
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		TimeTicker ticker = new TimeTicker();
		((TimeTickerToTick)ticker.getToTick()).addTickable(audioCueTimeline);
		ticker.startTicking();
//		audioCueTimeline.setPreWaitTime(new TimeElapsed(1000));
//		audioCueTimeline.setPostWaitTime(new TimeElapsed(0));
		audioCueTimeline.play();
	}

	/**
	 * Should Print Out Action Twice, once at 1 second, once at 2.5 seconds, once at 10 seconds.
	 */
	private static void addPrintActionAndPlay() {
		TickableTimeline basicTime = new TickableTimeline();
		TimeTicker ticker = new TimeTicker();
		((TimeTickerToTick)ticker.getToTick()).addTickable(basicTime);
		Action action = createPrintAction();
		basicTime.addActionAtTime(new TimeElapsed(1000), action);
		basicTime.addActionAtTime(new TimeElapsed(2500), action);
		basicTime.addActionAtTime(new TimeElapsed(10000), action);
		ticker.startTicking();
		basicTime.play();
	}

	private static Action createPrintAction() {
		return new Action() {
			
			@Override
			public void doAction() {
				System.out.println("ACTION");
			}
		};
	}
	
}
