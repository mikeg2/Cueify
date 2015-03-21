package com.cueify.cuelist.behavior;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.cue.Cue;
import com.cueify.cuelist.CueList;
import com.cueify.timeline.TimelineState;

public class CallStartedListener implements
		ChangeListener<TimelineState> {

	private CueList cueList;
	private Cue cue;
	
	public CallStartedListener(Cue cue, CueList cueList) {
		this.cue = cue;
		this.cueList = cueList;
	}
	
	@Override
	public void changed(ObservableValue<? extends TimelineState> arg0,
			TimelineState arg1, TimelineState arg2) {
		if (arg2 == TimelineState.PLAY && (arg1 == TimelineState.FINISHED || arg1 == TimelineState.STOPPED)) {
			cueList.callNextForCue(cue);
		}
	}

}
