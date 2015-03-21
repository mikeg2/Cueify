package com.cueify.timeline;

import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.value.ObservableValue;

import com.cueify.output.AudioOutput;
import com.cueify.time.TimeElapsed;
import com.cueify.timeline.tickable.WaitTimeline;

/**
 * Unlike a PassiveTimeline, an ActiveTimeline controls the position of
 * its playhead internally. It can also be controlled externally, but
 * the playhead naturally progresses.
 * @author Michael Goldfine
 *
 */
@XmlSeeAlso({WaitTimeline.class, AudioOutput.class})
public interface ActiveTimeline extends PassiveTimeline {
	/**
	 * Should start at point zero if it has not begun yet
	 * or, if it has, it should pick up from where it left off
	 */
	public void play();
	
	/**
	 * Plays from a certain point
	 */
	public void play(TimeElapsed startAtTime);
	
	/**
	 * Pauses, but does not reset the playable.
	 */
	public void pause();
	
	/**
	 * Pauses and resets the playhead of the playable
	 */
	public void stop();
	
	/**
	 * Sets the playhead of the playable at 0
	 */
	public void reset();
	
	@XmlTransient
	public TimelineState getState();

	@XmlTransient
	public void setState(TimelineState state);

	public ObservableValue<TimelineState> state();
}
