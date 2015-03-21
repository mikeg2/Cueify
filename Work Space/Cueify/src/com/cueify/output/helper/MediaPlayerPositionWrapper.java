package com.cueify.output.helper;

import com.cueify.time.TimeElapsed;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class MediaPlayerPositionWrapper {
	private MediaPlayer player;
	private SimpleObjectProperty<TimeElapsed> playheadProperty = new SimpleObjectProperty<>(new TimeElapsed(0));
	private ChangeListener<Duration> playerChangeListener = new ChangeListener<Duration>() {

		@Override
		public void changed(ObservableValue<? extends Duration> observable,
				Duration oldValue, Duration newValue) {
			long startTime = 0;
			if(player.getStartTime() != null) {
				startTime = (long) player.getStartTime().toMillis();
			}
			double position = newValue.toMillis();
			double playHead = position - startTime;
			TimeElapsed playHeadTE = new TimeElapsed((long) playHead);
			playheadProperty.setValue(playHeadTE);
		}
	};

	public MediaPlayerPositionWrapper(MediaPlayer player) {
		setPlayer(player);
	}

	public MediaPlayerPositionWrapper() {
	}

	private synchronized void bindPlayHeadAndPlayer() {
		player.currentTimeProperty().addListener(playerChangeListener);
		playheadProperty.addListener( new ChangeListener<TimeElapsed>() {

			@Override
			public void changed(
					ObservableValue<? extends TimeElapsed> observable,
					TimeElapsed oldValue, TimeElapsed newValue) {
				setPlayerToPlayheadPropertyValue(newValue);
			}
		});
		setPlayerToPlayheadPropertyValue(playheadProperty.get());
	}
	
	protected void setPlayerToPlayheadPropertyValue(TimeElapsed newValue) {
		double startTime = player.getStartTime().toMillis();
		double position = (long) newValue.getTimeInMilli() + startTime;
		Duration pos = new Duration(position);
		if(!pos.equals(player.currentTimeProperty()))
			player.seek(pos);		
	}

	public SimpleObjectProperty<TimeElapsed> playhead() {
		return playheadProperty;
	}
	
	public TimeElapsed getPlayhead() {
		return playheadProperty.get();
	}
	
	public void setPlayhead(TimeElapsed playHead) {
		playheadProperty.setValue(playHead);
	}

	public void setPlayer(MediaPlayer player) {
		if (this.player != null) {
			this.player.currentTimeProperty().removeListener(playerChangeListener);
		} else if (this.player == player) {
			return;
		}
		this.player = player;
		bindPlayHeadAndPlayer();		
	}
	
}
