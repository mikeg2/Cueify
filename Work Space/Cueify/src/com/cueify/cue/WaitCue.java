package com.cueify.cue;

import javafx.beans.property.ObjectProperty;

import com.cueify.time.TimeElapsed;
import com.cueify.timeline.tickable.WaitTimeline;
import com.cueify.timeline.tickable.cue.CueTimeline;

public class WaitCue extends Cue {
	
	public WaitCue() {
		this(new TimeElapsed(0));
	}
	
	public WaitCue(TimeElapsed time) {
		WaitTimeline contentWait = new WaitTimeline(time);
		this.mainTimeline = new CueTimeline(contentWait);
	}
	
	private WaitTimeline getWaitTimeline() {
		return (WaitTimeline) this.mainTimeline.getContent();
	}
	public TimeElapsed getWaitTime() {
		return getWaitTimeline().getWaitTime();
	}

	public void setWaitTime(TimeElapsed value) {
		getWaitTimeline().setWaitTime(value);
	}

	public ObjectProperty<TimeElapsed> waitTime() {
		return getWaitTimeline().waitTime();
	}

	@Override
	public void setCueTimeline(CueTimeline timeline) {
		((WaitTimeline)mainTimeline.getContent()).setWaitTime(((WaitTimeline)timeline.getContent()).getWaitTime());
	}
}
