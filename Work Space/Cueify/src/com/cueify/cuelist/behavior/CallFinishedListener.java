package com.cueify.cuelist.behavior;

import com.cueify.cue.Cue;
import com.cueify.cuelist.CueList;
import com.cueify.timeline.TimelineState;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class CallFinishedListener implements ChangeListener<TimelineState> {
	private CueList cueList;
	private Cue cue;
	
	public CallFinishedListener(Cue cue, CueList cueList) {
		this.cue = cue;
		this.cueList = cueList;
	}
	
	@Override
	public void changed(ObservableValue<? extends TimelineState> arg0,
			TimelineState arg1, TimelineState arg2) {
		System.out.println("CHANGE VALUE: " + arg1 + " to " + arg2);
		if (arg2 == TimelineState.FINISHED) {
			cueList.callNextForCue(cue);
		}
	}

}
