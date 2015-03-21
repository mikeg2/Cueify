package com.cueify.output.property.link.helper;

import javafx.beans.binding.Binding;
import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import com.cueify.time.TimeElapsed;
import com.cueify.time.TimeUnit;

public class PlayheadPercentWrapper {
	private SimpleObjectProperty<TimeElapsed> playhead = new SimpleObjectProperty<TimeElapsed>();
	private DoubleProperty percentLocation = new SimpleDoubleProperty();
	private ObservableValue<TimeElapsed> totalDuration = null;

	public PlayheadPercentWrapper(DoubleProperty percentLocation, ObservableValue<TimeElapsed> duration) {
		this.percentLocation = percentLocation;
		this.totalDuration = duration;
		connect();
	}

	private void connect() {
		playhead.bind(createPBinding());
		percentLocation.bind(createPLBinding());
	}

	private Binding<TimeElapsed> createPBinding() {
		return new ObjectBinding<TimeElapsed>() {
			
			{
				bind(percentLocation);
				bind(totalDuration);
			}
	
			@Override
			protected TimeElapsed computeValue() {
				return new TimeElapsed(TimeUnit.MILLISECOND, percentLocation.getValue()*totalDuration.getValue().getTime());
			}
		};
	}

	private ObservableValue<? extends Number> createPLBinding() {
		return new ObjectBinding<Double>() {
			
			{
				bind(playhead);
			}
			
			@Override
			protected Double computeValue() {
				return playhead.getValue().getTime() / (double) totalDuration.getValue().getTime();
			}
		};
	}
	
	// Getters and Setters
	public TimeElapsed getPlayhead() {
		return playhead.getValue();
	}

	public void setPlayhead(TimeElapsed value) {
		playhead.setValue(value);
	}

	public SimpleObjectProperty<TimeElapsed> playhead() {
		return playhead;
	}
}
