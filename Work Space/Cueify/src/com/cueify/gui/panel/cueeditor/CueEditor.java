package com.cueify.gui.panel.cueeditor;

import com.cueify.cue.Cue;
import com.cueify.cuelist.CueListItem;
import com.cueify.gui.panel.GUIPanelOrganizer;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.ViewModelDeleget;
import com.cueify.gui.panel.cueeditor.tabs.TabGUIManager;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class CueEditor extends TabPane implements ViewModelDeleget<CueEditorModel, CueEditor>{
	
	/**
	 * 
	 */
	public CueEditor(CueListItem toEdit) {
		setCue(toEdit);
	}
	
	public CueEditor() {
		setCue(null);
	}

	public void setCue(CueListItem toEdit) {
		CueEditorModel ref = new CueEditorModel();
		ref.cueListItemToEdit = toEdit;
		new GUIPanelOrganizer<CueEditorModel, CueEditor>(this, null, ref, this);
		setUpGUI(ref);
	}

	private void setUpGUI(CueEditorModel ref) {
		createNodeAndLink(ref);
	}

	private Node createNodeAndLink(CueEditorModel ref) {
		this.getTabs().clear();
		Tab[] tabs = getCueTabs(ref);
		TabPane node = this;
		node.getTabs().addAll(tabs);
		return node;
	}

	private Tab[] getCueTabs(CueEditorModel ref) {
		return TabGUIManager.getTabsForModel(ref);
	}

	@Override
	public GUIViewModel<CueEditorModel, CueEditor> createViewModel() {
		return new CueEditorViewModel();
	}
	
}
