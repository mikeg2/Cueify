package com.cueify.save.adapter.cue;

public class AdaptedTimeElapsed {
	private long timeInMilli;

	public AdaptedTimeElapsed() {
		
	}

	public AdaptedTimeElapsed(long time) {
		timeInMilli = time;
	}
	public long getTime() {
		return timeInMilli;
	}
	public void setTime(long time) {
		timeInMilli = time;
	}
}
