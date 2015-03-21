package com.cueify.output.property.link;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.beans.value.WritableValue;

import com.cueify.output.property.link.helper.PlayheadPercentWrapper;
import com.cueify.time.TimeElapsed;
import com.cueify.timeline.PassiveTimeline;

//TODO Add ability to change property type after the fact while preserving graph?
public class PropertyTimeline implements PassiveTimeline {
	private DoubleProperty percentLocation = new SimpleDoubleProperty();
	private ObjectProperty<TimeElapsed> totalDuration = new SimpleObjectProperty<>();
	private PlayheadPercentWrapper playhead = new PlayheadPercentWrapper(this.percentLocation, this.totalDuration);
	private PropertyGraphLink<?> valueGraph = null;

	public PropertyTimeline(WritableValue<?> property) {
		this.valueGraph = PropertyGraphFactory.createPropertyGraphForProperty(property);
		linkPropertyToGraphPercentLocation();
	}
	
	private void linkPropertyToGraphPercentLocation() {
		percentLocation.addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				valueGraph.setToPoint(newValue.doubleValue());
			}
		});
	}

	@Override
	public void movePlayheadTo(TimeElapsed atTime) {
		playhead.setPlayhead(atTime);
	}

	@Override
	public TimeElapsed getAbsoluteDuration() {
		return totalDuration.getValue();
	}

	public void setAbsoluteDuration(TimeElapsed duration) {
		totalDuration.setValue(duration);
	}

	@Override
	public TimeElapsed getPlayhead() {
		return playhead.getPlayhead();
	}

	@Override
	public ObjectProperty<TimeElapsed> playhead() {
		return playhead.playhead();
	}

	@Override
	public ObjectProperty<TimeElapsed> absoluteDuration() {
 		return totalDuration;
	}

}
