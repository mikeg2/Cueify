package com.cueify.gui.panel.cueeditor.tabs.wait;

import java.net.URL;

import com.cueify.gui.controls.validate.TimeElapsedField;
import com.cueify.gui.panel.GUIPanelOrganizer;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.ViewModelDeleget;
import com.cueify.gui.panel.cueeditor.CueEditorModel;
import com.cueify.gui.panel.cueeditor.tabs.TabGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.layout.GridPane;

public class CueEditorWait extends GridPane implements TabGUI, ViewModelDeleget<CueEditorModel, CueEditorWait>{
	final URL FXML_LOCATION = getClass().getResource("CueEditorWaitFXML.fxml");

	@FXML TimeElapsedField waitControl;

	public CueEditorWait(CueEditorModel ref) {
		new GUIPanelOrganizer<CueEditorModel, CueEditorWait>(this, FXML_LOCATION, ref, this);
	}

	@Override
	public String getName() {
		return "Wait";
	}

	@Override
	public Tab getTab() {
		Tab audio = new Tab(getName());
		audio.setContent(this);
		audio.setClosable(false);
		return audio;
	}

	@Override
	public GUIViewModel<CueEditorModel, CueEditorWait> createViewModel() {
		return new CueEditorWaitController();
	}

}
