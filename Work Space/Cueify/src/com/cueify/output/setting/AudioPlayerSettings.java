package com.cueify.output.setting;

import com.cueify.time.TimeElapsed;
import com.cueify.time.TimeUnit;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class AudioPlayerSettings {
	private SimpleObjectProperty<TimeElapsed> startTime = new SimpleObjectProperty<>(new TimeElapsed(0));
	private SimpleObjectProperty<TimeElapsed> stopTime = new SimpleObjectProperty<>(new TimeElapsed(0));
	private DoubleProperty volume = new SimpleDoubleProperty(1);
	private DoubleProperty rate = new SimpleDoubleProperty(1);
	private ObjectBinding<TimeElapsed> duration = new ObjectBinding<TimeElapsed>() {
		
		{
			bind(startTime);
			bind(stopTime);
			bind(rate);
		}

		@Override
		protected TimeElapsed computeValue() {
			try {
				return new TimeElapsed(TimeUnit.MILLISECOND, (stopTime.get().getTime() - startTime.get().getTime())/rate.get());
			} catch (NullPointerException e) {
				return null;
			}
		}
	};
	private DoubleProperty balance = new SimpleDoubleProperty();

	public synchronized void linkToMediaPlayer(MediaPlayer p) {
		p.setVolume(volume.doubleValue());
		volume.bindBidirectional(p.volumeProperty());
		p.setRate(rate.doubleValue());
		rate.bindBidirectional(p.rateProperty());
		p.setBalance(balance.doubleValue());
		balance.bindBidirectional(p.balanceProperty());
		if (stopTime.get().equals(new TimeElapsed(0)) && p.getMedia().getDuration() == Duration.UNKNOWN) {
			waitForTrueDurationThenAssign(p.getMedia().durationProperty(), stopTime);
		}  else if (stopTime.get().equals(new TimeElapsed(0))) {
			linkDurationAndTimeElapsed(p.getMedia().durationProperty(), stopTime);
		}
	}

	private void linkDurationAndTimeElapsed(
			ReadOnlyObjectProperty<Duration> durationProperty,
			SimpleObjectProperty<TimeElapsed> timeElapsed) {
//		new DurationToTimeElapsedWrapper(durationProperty).timeElapsed().bindBidirectional(timeElapsed);		
		timeElapsed.set(new TimeElapsed(durationProperty.get()));
	}

	private void waitForTrueDurationThenAssign(
			final ReadOnlyObjectProperty<Duration> durationProperty, final SimpleObjectProperty<TimeElapsed> timeElapsed) {
		durationProperty.addListener(new ChangeListener<Duration>() {

			@Override
			public void changed(ObservableValue<? extends Duration> observable,
					Duration oldValue, Duration newValue) {
				if (newValue != Duration.UNKNOWN) {
					linkDurationAndTimeElapsed(durationProperty, timeElapsed);
					durationProperty.removeListener(this);
				}
			}
		});
	}

	//Getters and Setters
	public SimpleObjectProperty<TimeElapsed> startTime() {
		return startTime;
	}

	public SimpleObjectProperty<TimeElapsed> stopTime() {
		return stopTime;
	}
	
	public ObjectBinding<TimeElapsed> duration() {
		return duration;
	}
	
	public DoubleProperty volume() {
		return volume;
	}
	
	public DoubleProperty rate() {
		return rate;
	}

	public DoubleProperty balance() {
		return balance ;
	}
}
