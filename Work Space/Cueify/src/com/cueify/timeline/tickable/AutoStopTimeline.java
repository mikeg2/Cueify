package com.cueify.timeline.tickable;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.time.TimeElapsed;
import com.cueify.timeline.ActiveTimeline;
import com.cueify.timeline.TimelineState;

public abstract class AutoStopTimeline implements ActiveTimeline {
	private final ObjectProperty<TimeElapsed> playhead = new SimpleObjectProperty<>(new TimeElapsed(0));

	public AutoStopTimeline() {
		playhead.addListener(new ChangeListener<TimeElapsed>() {

			@Override
			public void changed(
					ObservableValue<? extends TimeElapsed> observable,
					TimeElapsed oldValue, TimeElapsed newValue) {
				synchronized(AutoStopTimeline.this) {
					if(shouldStop())
						finish();
				}
			}
		});
	}


	protected abstract void finish();
	
	/**
	 * For the AutoStopTimeline, this is called to determine if it should stop.
	 * It can be overridden in order to change the auto behavior.
	 * @return True if it should stop. False if it should not.
	 */
	protected synchronized boolean shouldStop() {
		int lastActionCompare = getAbsoluteDuration().compareTo(this.getPlayhead());
		return lastActionCompare == -1 || lastActionCompare == 0;
	}
	
	@Override
	public TimeElapsed getPlayhead() {
		return this.playhead.getValue();
	}
	
	@Override
	public ObjectProperty<TimeElapsed> playhead() {
		return playhead;
	}

	//Always call super if override. This code prevents a bug causing the program to freeze if a timeline has a duration of 0
	@Override
	public synchronized void play() {
	}
}
