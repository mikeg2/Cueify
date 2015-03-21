package com.cueify.timeline.list;

import java.util.ArrayList;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.*;
import com.cueify.action.Action;
import com.cueify.time.TimeElapsed;

public class ActionTimeList {
	public static class ActionTimePair {
		private ObjectProperty<TimeElapsed> time = new SimpleObjectProperty<TimeElapsed>();
		private ObjectProperty<Action> action = new SimpleObjectProperty<Action>();
		
		public TimeElapsed getTime() {
			return time.getValue();
		}

		public void setTime(TimeElapsed time) {
			this.time.setValue(time);
		}

		public Action getAction() {
			return action.getValue();
		}

		public void setAction(Action action) {
			this.action.set(action);
		}

		public ActionTimePair(TimeElapsed time, Action action) {
			setAction(action);
			setTime(time);
		}

		public boolean isInTimeFrame(TimeElapsed fromNonInclusive,
				TimeElapsed toInclusive) {
			int compareValueFrom = this.getTime().compareTo(fromNonInclusive);
			int compareValueTo = this.getTime().compareTo(toInclusive);
			return compareValueFrom == 1 && compareValueTo == -1 || compareValueTo == 0;
		}
	}

	private ObservableList<ActionTimePair> actions = FXCollections.observableArrayList();

	public ActionTimePair[] getAllActionsInTimeFrame(
			TimeElapsed fromNonInclusive, TimeElapsed toInclusive) {
		ArrayList<ActionTimePair> pairsFound = new ArrayList<ActionTimePair>();
		for (ActionTimePair actionTimePair : actions) {
			if(actionTimePair.isInTimeFrame(fromNonInclusive, toInclusive)){
				pairsFound.add(actionTimePair);
			}
		}
		ActionTimePair[] pairsFoundAsArray = new ActionTimePair[pairsFound.size()];
		pairsFound.toArray(pairsFoundAsArray);
		return pairsFoundAsArray;
	}
	
	public TimeElapsed getTimeOfLastEvent() {
		if( this.actions.size() == 0)
			return new TimeElapsed(0);
		ActionTimePair lastAction = this.actions.get(0);
		for (ActionTimePair pair : this.actions) {
			if(pair.getTime().compareTo(lastAction.getTime()) == 1)
				lastAction = pair;
		}
		return lastAction.getTime();
	}
	
	public void addActionAtTime(TimeElapsed time, Action action) {
		ActionTimePair pair = new ActionTimePair(time, action);
		actions.add(pair);
	}
	
	public void addActionTimePair(ActionTimePair pair) {
		actions.add(pair);
	}
	
	public ObservableList<ActionTimePair> actions() {
		return actions;
	}
}
