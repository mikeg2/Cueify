package com.cueify.cue;

import com.cueify.output.AudioOutput;
import com.cueify.timeline.tickable.cue.CueTimeline;
import com.cueify.timeline.tickable.cue.CueTimelineFactory;

public class AudioCue extends MediaCue {
	
	public AudioCue() {
		this.mainTimeline = CueTimelineFactory.getAudioCueTimeline();
	}

	public AudioOutput getAudioOutput() {
		return (AudioOutput) getOutput();
	}
	
	/**
	 * DO NOT USE! This method is for JAXB use only.
	 * @param timeline
	 */
	public void setCueTimeline(CueTimeline timeline) {
		super.setCueTimeline(timeline);
		AudioOutput copyTo = (AudioOutput) mainTimeline.getContent();
		AudioOutput copyFrom = (AudioOutput) timeline.getContent();
		copyTo.setSource(copyFrom.getSource());
		copyTo.setRate(copyFrom.getRate());
		copyTo.setStartTime(copyFrom.getStartTime());
		copyTo.setStopTime(copyFrom.getStopTime());
		copyTo.setVolume(copyFrom.getVolume());
	}
}
