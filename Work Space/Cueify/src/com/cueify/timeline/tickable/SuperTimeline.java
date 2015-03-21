package com.cueify.timeline.tickable;

import javafx.beans.binding.ObjectBinding;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.ticker.Tickable;
import com.cueify.time.TimeElapsed;
import com.cueify.timeline.ActiveTimeline;
import com.cueify.timeline.PassiveTimeline;
import com.cueify.timeline.TimelineState;
import com.cueify.timeline.list.SubTimelineList;

/**
 * SuperTimeline contains "sub-timelines", which must be objects that implement
 * the com.cuify.timeline.ActiveTimeline interface. It combines these "sub-timelines"
 * into one timeline, where they are played on after the other.
 * 
 * NOTE: Actions outside of the subtimeline's time will not currently be executed.
 * 
 * @author Michael Goldfine
 *
 */

/* 
 Need for lazy loading. */
public class SuperTimeline extends TickableTimeline {
	private SubTimelineList subTimelines = new SubTimelineList(); // Instantiated in instantiateAbsoluteDuration() method.
	private Integer onTimeline = 0;
	
	// This variable is set to true before ANY changes to the state of the player
	// it is then set again to false.
	private boolean resetMode = false;

	@Override
	public void tick(TimeElapsed timeSinceLastTick) {
		try {
			if (this.getState().equals(TimelineState.PLAY)) {
				tickNecissarySubTimelines(timeSinceLastTick);
				syncPlayheadToSubTimelines();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public ObjectBinding<TimeElapsed> absoluteDuration() {
		final ObservableValue<TimeElapsed> ad = super.absoluteDuration();
		return new ObjectBinding<TimeElapsed>() {
			
			{
				bind(ad);
				bind(subTimelines.subTimelines());
   
			}

			@Override
			protected TimeElapsed computeValue() {
				return getTimeOfLastEvent();
			}
		};
	}

	@Override
	public TimeElapsed getAbsoluteDuration() {
		return absoluteDuration().getValue();
	}

	public ActiveTimeline getCurrentTimeline() {
		return subTimelines.getTimelineAtIndex(onTimeline);
	}

	private synchronized void syncPlayheadToSubTimelines() {
		playhead().setValue(calculateCurrentPlayheadFromSub());
		this.moveAndExecutePlayheadTo(playhead().getValue());
	}

	private TimeElapsed calculateCurrentPlayheadFromSub() {
		TimeElapsed calculatedPlayhead = subTimelines.calculateTimeOfSubTimelinesUpToIndex(onTimeline);
		calculatedPlayhead = calculatedPlayhead.add(getCurrentTimeline().getPlayhead());
		return calculatedPlayhead;
	}

	private void tickNecissarySubTimelines(TimeElapsed timeSinceLastTick) {
		for (ActiveTimeline tl : subTimelines) {
			if (tl instanceof Tickable) {
				((Tickable)tl).tick(timeSinceLastTick);
			}
		}
	}

	@Override
	public void play() {
		super.play();
		getCurrentTimeline().play();
	}

	@Override
	public synchronized void pause() {
		super.pause();
		for (ActiveTimeline tl : subTimelines) {
			tl.pause();
		}
	}

	@Override
	public synchronized void reset() {
		System.out.println("RESET");
	
		resetMode = true;
		super.reset();
		this.onTimeline = 0;

		for (ActiveTimeline tl : subTimelines) {
			tl.reset();
			if (subTimelines.numberOfSubTimelines() > 0 && subTimelines.getTimelineAtIndex(0) != tl) {
				tl.stop();
			}
		}
		if(subTimelines.numberOfSubTimelines() > 0) {
			ActiveTimeline tl = subTimelines.getTimelineAtIndex(0);
			if (this.getState() == TimelineState.STOPPED) {
				tl.stop();
			} else if (this.getState() == TimelineState.PAUSE) {
				tl.pause();
			} else if (this.getState() == TimelineState.PLAY) { 
				tl.play();
			} else {
				tl.stop();
			}
		}
		resetMode = false;
	}

	@Override
	public synchronized void stop() {
		resetMode = true;
		super.stop();
		resetMode = false;
	}
	
	@Override
	public synchronized void finish() {
		resetMode = true;
		super.finish();
		for (ActiveTimeline timeline : subTimelines) {
			System.out.println(timeline.toString() + " : " + timeline.getState());
		}
		resetMode = false;
	}

	public TimeElapsed getTimeOfLastEvent() {
		TimeElapsed subTimelineLast = subTimelines.getStaticDurationOfSubTimelines();
		TimeElapsed oldTimeLast = super.absoluteDuration().getValue();
		return subTimelineLast.compareTo(oldTimeLast) == 1 ? subTimelineLast : oldTimeLast;
	}
	
	public void addTimelineAtIndex(ActiveTimeline timeline, int index) {
		addFinishNextListener(timeline);
		subTimelines.addTimelineAtIndex(index, timeline);
	}
	
	public void addTimeline(ActiveTimeline timeline) {
		addFinishNextListener(timeline);
		this.subTimelines.addTimeline(timeline);
	}

	private void addFinishNextListener(ActiveTimeline timeline) {
		final ActiveTimeline thisTimeline = timeline;
		timeline.state().addListener(new ChangeListener<TimelineState>() {
			@Override
			public void changed(ObservableValue<? extends TimelineState> observable,
					TimelineState oldValue, TimelineState newValue) {
				if (resetMode) { // Prevents loops from starting when the state of the timeline is changing.
					return;
				}
				if(subTimelines.getTimelineAtIndex(onTimeline) != thisTimeline) {
					System.out.println("ON-TIMELINES");
					return;
				}
				if(newValue==TimelineState.FINISHED) {
						System.out.println("In Finish Loop");
						synchronized(SuperTimeline.this.onTimeline) {
							onTimeline++;
						}
						if (onTimeline == subTimelines.numberOfSubTimelines()) {
							finish();
							return;
						}
						ActiveTimeline nextTimeline = subTimelines.getTimelineAtIndex(onTimeline);
						nextTimeline.play();
					System.out.println("------NEXT TIMELINE:" + onTimeline + "-----");
				} else {
//					System.out.println("Setting State: " + newValue);
//					thisSuper.setState(newValue);
				}
			}
		});
	}
}
