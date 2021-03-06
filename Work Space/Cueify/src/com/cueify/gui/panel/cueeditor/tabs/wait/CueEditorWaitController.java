package com.cueify.gui.panel.cueeditor.tabs.wait;

import com.cueify.cue.WaitCue;
import com.cueify.gui.panel.BasicGUIViewModel;
import com.cueify.gui.panel.cueeditor.CueEditorModel;

public class CueEditorWaitController extends BasicGUIViewModel<CueEditorModel, CueEditorWait> {
	
	@Override
	public void linkControllerToView(CueEditorWait panel) {
		WaitCue cue = (WaitCue) getModel().cueListItemToEdit.getCue();
		panel.waitControl.timeElapsed().bindBidirectional(cue.waitTime());
	}


}
