package com.cueify.time;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Duration;

//TODO Possibly make mutable version of TimeElapsed so you don't go through SO many objects.
public class DurationToTimeElapsedWrapper {
	private SimpleObjectProperty<Duration> d = null;
	private SimpleObjectProperty<TimeElapsed> t = new SimpleObjectProperty<TimeElapsed>();

	public DurationToTimeElapsedWrapper(SimpleObjectProperty<Duration> d) {
		this.d = d;
		bindTimeTo();
		bindDurationTo(d);
	}

	public DurationToTimeElapsedWrapper(ObservableValue<Duration> d) {
		bindDurationTo(d);
	}

	private void bindTimeTo() {
		d.set(new Duration(t.get().getTime(TimeUnit.MILLISECOND)));
		t.addListener(new ChangeListener<TimeElapsed>() {

			@Override
			public void changed(
					ObservableValue<? extends TimeElapsed> observable,
					TimeElapsed oldValue, TimeElapsed newValue) {
				d.set(newValue.toDuration());
			}
		});
	}
	
	private void bindDurationTo(final ObservableValue<Duration> d) {
		t.setValue(new TimeElapsed(d.getValue()));
		d.addListener(new ChangeListener<Duration>() {

			@Override
			public void changed(ObservableValue<? extends Duration> observable,
					Duration oldValue, Duration newValue) {
				if (newValue == Duration.UNKNOWN) {
					t.setValue(TimeElapsed.UKNOWN);
				} else {
					t.setValue(new TimeElapsed(newValue));
				}
			}
		});
	}

	public SimpleObjectProperty<TimeElapsed> timeElapsed() {
		return t;
	}

	public SimpleObjectProperty<Duration> duration() {
		return d;
	}

}
