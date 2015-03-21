package com.cueify.timeline.tickable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

import com.cueify.action.Action;
import com.cueify.ticker.Tickable;
import com.cueify.time.TimeElapsed;
import com.cueify.timeline.ActionableTimeline;
import com.cueify.timeline.TimelineState;
import com.cueify.timeline.list.ActionTimeList;
import com.cueify.timeline.list.ActionTimeList.ActionTimePair;

//TODO Find clean way to update absoluteDuration property.
@XmlAccessorType(XmlAccessType.NONE)
public class TickableTimeline extends AutoStopTimeline implements Tickable, ActionableTimeline {


	protected ActionTimeList actions = new ActionTimeList();
	
	@XmlTransient
	private ObjectProperty<TimelineState> state = new SimpleObjectProperty<TimelineState>(TimelineState.FINISHED);

	private ObjectBinding<TimeElapsed> absoluteDuration;

	@Override
	public void play() {
		this.setState(TimelineState.PLAY);
		super.play();
	}

	@Override
	public void play(TimeElapsed startAtTime) {
		synchronized (playhead()) {
			movePlayheadTo(startAtTime);
			play();	
		}
	}

	@Override
	public void pause() {
		System.out.println(getState() + "------PAUSED------" + this);
		this.setState(TimelineState.PAUSE);
	}

	@Override
	public void stop() {
		this.pause();
		this.setState(TimelineState.STOPPED);
		this.reset();
		System.out.println("------STOPED------" + this);
	}

	@Override
	public void reset() {
		this.movePlayheadTo(new TimeElapsed(0));
	}

	protected synchronized void finish() {
		setState(TimelineState.FINISHED);;
		this.pause();
		this.reset();
		System.out.println(getState() == TimelineState.FINISHED);
		System.out.println("COMPLETE (" + this + ")");
	}

	@Override
	public synchronized void movePlayheadTo(final TimeElapsed atTime) {
		try {
			javafx.application.Platform.runLater(new Runnable() {
				@Override
				public void run() {
					playhead().setValue(atTime);					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public synchronized void moveAndExecutePlayheadTo(TimeElapsed atTime) {
		TimeElapsed oldPlayHeadValue = playhead().getValue();
		movePlayheadTo(atTime);
		TimeElapsed newPlayHeadValue = playhead().getValue();
		doAllActionsInTimeFrame(oldPlayHeadValue, newPlayHeadValue);
	}

	@Override
	public TimeElapsed getAbsoluteDuration() {
		return absoluteDuration().getValue();
	}

	@Override
	public TimelineState getState() {
		return this.state.getValue();
	}

	@Override
	public void setState(TimelineState state) {
		this.state.setValue(state);
	}

	@Override
	public synchronized ObjectProperty<TimelineState> state() {
		return this.state;
	}

	private void doAllActionsInTimeFrame(TimeElapsed fromNonInclusive,
			TimeElapsed toInclusive) {
		ActionTimePair[] inTimeFrame = actions.getAllActionsInTimeFrame(fromNonInclusive, toInclusive);
		for (ActionTimePair actionTimePair : inTimeFrame) {
			actionTimePair.getAction().doAction();
		}
	}

	@Override
	public synchronized void tick(TimeElapsed timeSinceLastTick) {
		if (getState() == TimelineState.PLAY) {
			TimeElapsed newPlayHeadValue = getPlayhead().add(timeSinceLastTick);
			moveAndExecutePlayheadTo(newPlayHeadValue);
		}
	}

	public void addActionAtTime(TimeElapsed time, Action action) {
		actions.addActionAtTime(time, action);
	}

	public void addActionTimePair(ActionTimePair pair) {
		actions.addActionTimePair(pair);
	}

	@Override
	public ObservableValue<TimeElapsed> absoluteDuration() {
		if(absoluteDuration == null) {
			absoluteDuration = new ObjectBinding<TimeElapsed>() {
				
				{
					bind(actions.actions());
				}
	
				@Override
				protected TimeElapsed computeValue() {
					return actions.getTimeOfLastEvent();
				}
			};
		}
		return absoluteDuration;
	}


}