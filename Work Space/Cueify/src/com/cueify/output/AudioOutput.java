package com.cueify.output;

import java.net.MalformedURLException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableBooleanValue;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;

import com.cueify.output.helper.DurationTimeElapsedProperty;
import com.cueify.output.setting.AudioPlayerSettings;
import com.cueify.time.TimeElapsed;

@XmlRootElement
@XmlAccessorType(XmlAccessType.NONE)
public class AudioOutput extends JavaFxMediaBasicOutput {
	@XmlTransient
	private MediaPlayer player;

	@XmlTransient
	private AudioPlayerSettings settings = new AudioPlayerSettings();
	
	@XmlTransient
	private DurationTimeElapsedProperty mppWrapper = new DurationTimeElapsedProperty();
	
	@XmlTransient
	private BooleanProperty ready = new SimpleBooleanProperty(false);
	
	public AudioOutput() {
		System.out.println("AUDIO TIMELINE");
		source().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				stop();
				updateMediaPlayer();
			}
		});
	}

	private void updateMediaPlayer() {
		try {
			System.out.println("Setting Up Media Player:" + this);
			Media m = new Media(getSource().getAbsoluteFile().toURI().toURL().toString());
			player = new MediaPlayer(m);
			if (player.getStatus() != Status.UNKNOWN) {
				ready.set(true);
			} else {
				player.statusProperty().addListener(new ChangeListener<MediaPlayer.Status>() {
					
					@Override
					public void changed(
							ObservableValue<? extends MediaPlayer.Status> observable,
							MediaPlayer.Status oldValue, MediaPlayer.Status newValue) {
						System.out.println("CHANGE: "  + newValue);
						if (newValue != Status.UNKNOWN) {
							ready.set(true);
							observable.removeListener(this);
						};
					}
				});
			}
			player.setOnEndOfMedia(new Runnable() {
				
				@Override
				public void run() {
					finish();				
				}
			});
			mppWrapper.setPlayer(player);
			settings.linkToMediaPlayer(player);
			mppWrapper.multiplyer().bind(settings.rate());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void play() {
		super.play();
		System.out.println("ASK TO PLAY:" + this);
		if (isReady() == true) {
			player.play();
		} else {
			ready().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable,
						Boolean oldValue, Boolean newValue) {
					if(newValue.booleanValue() == true) {
						System.out.println("PLAY");
						play(); // calls self;
						ready().removeListener(this);
					}
				}
			});
		}
	}

	@Override
	public void play(TimeElapsed startAtTime) {
		if(player != null) {
			player.play();
			super.play();
		} else {
			finish();
		}
	}

	@Override
	public void movePlayheadTo(TimeElapsed atTime) {
		if(player != null)
			player.seek(atTime.toDuration());
	}

	@Override
	public void pause() {
		super.pause();
		if(player != null)
			player.pause();
	}

	@Override
	public void stop() {
		super.stop();
		if(player != null)
			player.stop();
	}

	@Override
	public void reset() {
		if(player != null)
			player.seek(player.getStartTime());
	}
	
	@Override
	protected void finish() {
		super.finish();
		if(player != null)
			player.stop();
	}

	@Override
	public TimeElapsed getAbsoluteDuration() {
		return settings.duration().getValue();
	}

	@Override
	public TimeElapsed getPlayhead() {
		return mppWrapper.get();
	}

	@Override
	public ObjectProperty<TimeElapsed> playhead() {
		return mppWrapper;
	}

	@XmlElement
	public void setStartTime(TimeElapsed timeElapsed) {
		settings.startTime().set(timeElapsed);
	}
	
	public TimeElapsed getStartTime() {
		return settings.startTime().get();
	}
	
	public SimpleObjectProperty<TimeElapsed> startTime() {
		return settings.startTime();
	}

	@XmlElement
	public void setStopTime(TimeElapsed stopTime) {
		settings.stopTime().set(stopTime);
	}
	
	public TimeElapsed getStopTime() {
		return settings.stopTime().get();
	}
	
	public SimpleObjectProperty<TimeElapsed> stopTime() {
		return settings.stopTime();
	}

	@XmlElement
	public double getVolume() {
		return settings.volume().getValue();
	}

	public void setVolume(double value) {
		settings.volume().setValue(value);
	}

	public DoubleProperty volume() {
		return settings.volume();
	}

	@XmlElement
	public double getRate() {
		return settings.rate().getValue();
	}

	public void setRate(double value) {
		settings.rate().setValue(value);
	}

	public DoubleProperty rate() {
		return settings.rate();
	}

	@XmlElement
	public double getBalance() {
		System.out.println(settings.balance().getValue().doubleValue());
		return settings.balance().getValue().doubleValue();
	}

	public void setBalance(double value) {
		settings.balance().setValue(value);
	}

	public DoubleProperty balance() {
		return settings.balance();
	}

	@Override
	public ObservableValue<TimeElapsed> absoluteDuration() {
		return settings.duration();
	}

	@Override
	public boolean isReady() {
		return ready.getValue();
	}

	@Override
	public ObservableBooleanValue ready() {
		return new ReadOnlyBooleanWrapper(ready, "Ready");
	}
	
	protected BooleanProperty readyProperty() {
		return ready;
	}
}
