package com.cueify.cuelist.behavior;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.cue.Cue;
import com.cueify.cuelist.CueList;
import com.cueify.timeline.TimelineState;
import com.cueify.undo.Undoable;

public class CueItemBehaviorBinder {
	public static Undoable addBehaviorToCueForList(ListItemCallNextBehavior behavior, Cue c, CueList l) {
		switch (behavior) {
		case CALL_AT_START:
			ChangeListener<TimelineState> state = new CallStartedListener(c, l);
			ObservableValue<TimelineState> prop = c.getCueTimeline().state();
			prop.addListener(state);
			return new AddListenerUndoable<TimelineState>(state, prop);
		case CALL_AFTER_PREWAIT:
			ChangeListener<TimelineState> state1 = new CallFinishedListener(c, l);
			ObservableValue<TimelineState> prop1 = c.getCueTimeline().getPreWait().state();
			prop1.addListener(state1);
			return new AddListenerUndoable<TimelineState>(state1, prop1);
		case CALL_AFTER_CONTENT:
			ChangeListener<TimelineState> state2 = new CallFinishedListener(c, l);
			ObservableValue<TimelineState> prop2 = c.getCueTimeline().getContent().state();
			prop2.addListener(state2);
			return new AddListenerUndoable<TimelineState>(state2, prop2);
		case CALL_AFTER_FINISHED:
			ChangeListener<TimelineState> state3 = new CallFinishedListener(c, l);
			ObservableValue<TimelineState> prop3 = c.getCueTimeline().state();
			prop3.addListener(state3);
			return new AddListenerUndoable<>(state3, prop3);
		default:
			return null;
		}
	}

}
