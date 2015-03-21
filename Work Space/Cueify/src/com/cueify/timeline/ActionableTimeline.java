package com.cueify.timeline;

import com.cueify.action.Action;
import com.cueify.time.TimeElapsed;

public interface ActionableTimeline extends ActiveTimeline {
	public void addActionAtTime(TimeElapsed time, Action action);
}
