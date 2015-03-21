package com.cueify.cue;

import com.cueify.output.BasicOutput;

/*
 * The `CueTimeline` of all subclasses should have an Output as their content.
 * */
public abstract class MediaCue extends Cue{
	
	public BasicOutput getOutput() {
		return (BasicOutput) getCueTimeline().getContent();
	}

}
