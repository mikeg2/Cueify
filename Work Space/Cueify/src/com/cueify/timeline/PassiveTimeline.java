package com.cueify.timeline;

import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;

import com.cueify.time.TimeElapsed;

/**
 * A PassiveTimeline's playhead is moved and positioned by another object.
 * It has no control over the movement of the playhead. All that is managed
 * externally.
 * @author Michael Goldfine
 *
 */
public interface PassiveTimeline {
	
	/**
	 * Sets the start time. Should work just as if it was paused at a certain time
	 * then played again
	 */
	public void movePlayheadTo(TimeElapsed atTime);

	
	/**
	 * Calculates the duration of the timeline, or, more specifically, the
	 * distance between the first event and the last event in the timeline.
	 * 
	 * An event is anything that happens, at all.
	 * @return The duration of the timeline
	 */
	public TimeElapsed getAbsoluteDuration();
	
	public ObservableValue<TimeElapsed> absoluteDuration();

	/**
	 * Gets the position of the playhåead, which should never be more
	 * than the duration (ie. getStaticDuration()).
	 * @return The position of the playHead.
	 */
	public TimeElapsed getPlayhead();
	
	/**
	 * Gets property of playhead
	 */
	public ObjectProperty<TimeElapsed> playhead();
}
