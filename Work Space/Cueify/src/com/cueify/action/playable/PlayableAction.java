package com.cueify.action.playable;

import com.cueify.action.Action;
import com.cueify.timeline.ActiveTimeline;

public abstract class PlayableAction implements Action {
	private ActiveTimeline target;

	public ActiveTimeline getTarget() {
		return target;
	}
 
	public void setTarget(ActiveTimeline target) {
		this.target = target;
	}

	
}
