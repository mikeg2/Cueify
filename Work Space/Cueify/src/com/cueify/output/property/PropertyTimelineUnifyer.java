package com.cueify.output.property;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.*;

import com.cueify.output.property.link.PropertyTimeline;
import com.cueify.time.TimeElapsed;
import com.cueify.timeline.PassiveTimeline;

public abstract class PropertyTimelineUnifyer implements PassiveTimeline {
	private SimpleObjectProperty<TimeElapsed> totalDuration = new SimpleObjectProperty<>();
	private SimpleObjectProperty<TimeElapsed> playhead = new SimpleObjectProperty<>();
	private ObservableMap<String, PropertyTimeline> propertyTimelines = FXCollections.observableHashMap();

	public PropertyTimelineUnifyer(PassiveTimeline t) {
		instantiateDefaultPropertyTimelines();
		playhead.bind(t.playhead());
		totalDuration.bind(t.absoluteDuration());
	}

	protected abstract void instantiateDefaultPropertyTimelines();

	// MUST BE CALLED ON ALL TIMELINES ADDED IN SUBTIMELINES
	protected void syncPropertyTimeline(PropertyTimeline timeline) {
		timeline.absoluteDuration().bind(totalDuration);
		timeline.playhead().bind(playhead);
	}

	@Override
	public void movePlayheadTo(TimeElapsed atTime) {
		playhead.setValue(atTime);
	}

	@Override
	public TimeElapsed getAbsoluteDuration() {
		return totalDuration.get();
	}

	@Override
	public TimeElapsed getPlayhead() {
		return playhead.get();
	}

	@Override
	public ObjectProperty<TimeElapsed> playhead() {
		return playhead();
	}

	@Override
	public ObservableValue<TimeElapsed> absoluteDuration() {
		return totalDuration;
	}

}
