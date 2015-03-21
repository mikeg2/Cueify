package com.cueify.timeline.list;

import java.util.Iterator;

import javafx.collections.*;

import com.cueify.time.TimeElapsed;
import com.cueify.timeline.ActiveTimeline;

public class SubTimelineList implements Iterable<ActiveTimeline>{
	private ObservableList<ActiveTimeline> subTimelines = FXCollections.observableArrayList();

	@Override
	public Iterator<ActiveTimeline> iterator() {
		return subTimelines.iterator();
	}
	
	public int numberOfSubTimelines() {
		return subTimelines.size();
	}

	public void addTimelineAtIndex(int index, ActiveTimeline timeline) {
		if(index >= this.numberOfSubTimelines()) {
			index = this.numberOfSubTimelines() - 1;
		}
		this.subTimelines.add(index, timeline);
	}
	
	public void addTimeline(ActiveTimeline timeline) {
		this.subTimelines.add(timeline);
	}

	public ActiveTimeline getTimelineAtIndex(int index) {
		return this.subTimelines.get(index);
	}

	public TimeElapsed getStaticDurationOfSubTimelines() {
		TimeElapsed totalDuration = calculateTimeOfSubTimelinesUpToIndex(subTimelines.size());
		return totalDuration;
	}
	

	public TimeElapsed calculateTimeOfSubTimelinesUpToIndex(int upToButNotIncluding) {
		TimeElapsed calculatedPlayhead = new TimeElapsed(0);
		for (int i = 0; i < upToButNotIncluding; i++) {
			ActiveTimeline subTimeline = subTimelines.get(i);
			calculatedPlayhead = calculatedPlayhead.add(subTimeline.getAbsoluteDuration());
		}
		return calculatedPlayhead;
	}
	
	public ObservableList<ActiveTimeline> subTimelines() {
		return subTimelines;
	}
}
