package com.cueify.gui.panel.cueeditor.tabs.basic;

import com.cueify.cue.Cue;
import com.cueify.gui.panel.BasicGUIViewModel;
import com.cueify.gui.panel.cueeditor.CueEditorModel;

public class CueEditorBasicsController extends BasicGUIViewModel<CueEditorModel, CueEditorBasics> {
	

	@Override
	public void linkControllerToView(CueEditorBasics panel) {
		Cue c = getModel().cueListItemToEdit.getCue();
		panel.preWaitField.timeElapsed().bindBidirectional(c.getCueTimeline().getPreWait().waitTime());
		panel.postWaitField.timeElapsed().bindBidirectional(c.getCueTimeline().getPostWait().waitTime());
		panel.numberField.number().bindBidirectional(c.cueNumber());
		panel.titleField.textProperty().bindBidirectional(c.name());
		panel.callNextBehaviorField.valueProperty().bindBidirectional(getModel().cueListItemToEdit.callNextBehavior());
	}

}
