package com.cueify.output.helper;

import java.util.Collections;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.cueify.time.TimeElapsed;
import com.cueify.time.TimeUnit;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class DurationTimeElapsedProperty extends ObjectProperty<TimeElapsed> {
	
	private ReadOnlyObjectProperty<Duration> duration = null;
	private MediaPlayer player = null;
	private DoubleProperty multiplyer = new SimpleDoubleProperty(1.0);

	private ChangeListener<TimeElapsed> changeLBind = new ChangeListener<TimeElapsed>() {

		@Override
		public void changed(
				ObservableValue<? extends TimeElapsed> observable,
				TimeElapsed oldValue, TimeElapsed newValue) {
			player.seek(convertTimeElapsedToDuration(newValue));
		}
		
	};
	private Set<ChangeListener<? super TimeElapsed>> changeListeners = Collections.newSetFromMap(new ConcurrentHashMap());
	private Set<InvalidationListener> invalidationListeners = Collections.newSetFromMap(new ConcurrentHashMap());
	private ObservableValue<? extends TimeElapsed> boundTo = null;

	private TimeElapsed convertDurationToTimeElapsed(Duration duration) {
		return new TimeElapsed(TimeUnit.MILLISECOND, duration.toMillis()/multiplyer.get());
	}

	private Duration convertTimeElapsedToDuration(TimeElapsed elapsed) {
		return elapsed.toDuration().multiply(multiplyer.get());
	}

	public void setPlayer(MediaPlayer player) {
		TimeElapsed oldValue = this.get();
		this.player = player;
		this.duration = player.currentTimeProperty();
		final ObjectProperty<TimeElapsed> me = this;
		this.multiplyer.bind(player.rateProperty());
		duration.addListener(new ChangeListener<Duration>() {

			@Override
			public void changed(ObservableValue<? extends Duration> observable,
					Duration oldValue, Duration newValue) {
				for (ChangeListener<? super TimeElapsed> changeL : changeListeners) {
					changeL.changed(me , convertDurationToTimeElapsed(oldValue), convertDurationToTimeElapsed(newValue));
				}
				for (InvalidationListener il : invalidationListeners) {
					il.invalidated(observable);
				}
			}
		});
		invalidate(oldValue);
	}

	private void invalidate(final TimeElapsed oldValue) {
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				synchronized (invalidationListeners) {
					for (InvalidationListener i : invalidationListeners) {
						i.invalidated(DurationTimeElapsedProperty.this);
					}	
				}
				synchronized (changeListeners) {
					for (ChangeListener<? super TimeElapsed> cl : changeListeners) {
						cl.changed(DurationTimeElapsedProperty.this, oldValue, DurationTimeElapsedProperty.this.get());
					}
				}
			}
			
		});
	}

	@Override
	public void bind(ObservableValue<? extends TimeElapsed> arg0) {
		arg0.addListener(changeLBind);
		boundTo = arg0;
	}

	@Override
	public boolean isBound() {
		return boundTo == null;
	}

	@Override
	public void unbind() {
		boundTo.removeListener(changeLBind);
		boundTo = null;
	}

	@Override
	public Object getBean() {
		return null;
	}

	@Override
	public String getName() {
		return "";
	}

	@Override
	public void addListener(final ChangeListener<? super TimeElapsed> arg0) {
		changeListeners.add(arg0);
	}

	@Override
	public void removeListener(ChangeListener<? super TimeElapsed> arg0) {
		changeListeners.remove(arg0);
	}

	@Override
	public void addListener(InvalidationListener arg0) {
		invalidationListeners.add(arg0);
	}

	@Override
	public void removeListener(InvalidationListener arg0) {
		invalidationListeners.remove(arg0);
	}

	@Override
	public TimeElapsed getValue() {
		if (player == null) {
			return new TimeElapsed(0);
		}
		return convertDurationToTimeElapsed(this.duration.get());
	}

	@Override
	public void setValue(TimeElapsed arg0) {
		player.seek(convertTimeElapsedToDuration(arg0));
	}
	
	@Override
	public void set(TimeElapsed arg0) {
		this.setValue(arg0);
	}
	
	@Override
	public TimeElapsed get() {
		return this.getValue();
	}
	
	
	// Setters and Getters
	public double getMultiplyer() {
		return multiplyer.getValue();
	}

	public void setMultiplyer(double value) {
		multiplyer.setValue(value);
	}

	public DoubleProperty multiplyer() {
		return multiplyer;
	}
	
}
