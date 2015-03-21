package com.cueify.timeline;

/**
 * Defines the state of a timeline.
 * 
 * PLAY: When the timeline is playing. As in, when its playhead is moving forward
 * PAUSE: When the timeline is paused. As in, when its playhead is not moving
 * FINISHED: When a timeline has reached its end. Should remain this way until played
 * @author Mike
 *
 */
public enum TimelineState {
	PLAY, // The playhead is moving 
	PAUSE, // The playhead is still and could be positioned anywhere
	STOPPED, // The playhead is still and is positioned at the start
	FINISHED // The Timeline is stopped, but it was stopped because it completed (Auto-Stop);
}
