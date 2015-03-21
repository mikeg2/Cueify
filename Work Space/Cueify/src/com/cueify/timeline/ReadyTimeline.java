package com.cueify.timeline;

import javafx.beans.value.ObservableBooleanValue;

public interface ReadyTimeline extends ActiveTimeline {
	public boolean isReady();
	public ObservableBooleanValue ready();
}
