package com.cueify.gui.panel.cueeditor.tabs.audio;

import javafx.scene.Node;

import com.cueify.cue.AudioCue;
import com.cueify.cue.Cue;
import com.cueify.gui.panel.BasicGUIViewModel;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.cueeditor.CueEditorModel;
import com.cueify.gui.panel.cueeditor.tabs.basic.CueEditorBasics;

public class CueEditorAudioController extends BasicGUIViewModel<CueEditorModel, CueEditorAudio> {
	
	@Override
	public void linkControllerToView(CueEditorAudio panel) {
		AudioCue cue = (AudioCue) getModel().cueListItemToEdit.getCue();
		((CueEditorAudio) panel).volumeControl.valueProperty().bindBidirectional(cue.getAudioOutput().volume());
		((CueEditorAudio) panel).sourceControl.file().set(cue.getAudioOutput().getSource());
		((CueEditorAudio) panel).sourceControl.file().bindBidirectional(cue.getAudioOutput().source());
		((CueEditorAudio) panel).rateControl.valueProperty().bindBidirectional(cue.getAudioOutput().rate());
		((CueEditorAudio) panel).balanceControl.valueProperty().bindBidirectional(cue.getAudioOutput().balance());
		((CueEditorAudio) panel).startTimeControl.timeElapsed().bindBidirectional(cue.getAudioOutput().startTime());
		((CueEditorAudio) panel).endTimeControl.timeElapsed().bindBidirectional(cue.getAudioOutput().stopTime());		
	}

}
