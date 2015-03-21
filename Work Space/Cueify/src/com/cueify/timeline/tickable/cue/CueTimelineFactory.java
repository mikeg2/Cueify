package com.cueify.timeline.tickable.cue;

import com.cueify.output.AudioOutput;
import com.cueify.output.BasicOutput;

public class CueTimelineFactory {
	public static CueTimeline getAudioCueTimeline() {
		BasicOutput content = new AudioOutput();
		CueTimeline toMake = new CueTimeline(content);
		return toMake;
	}
	
}
