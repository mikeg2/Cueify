package com.cueify.gui.panel.cueeditor.tabs;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.control.Tab;

import com.cueify.cue.AudioCue;
import com.cueify.cue.Cue;
import com.cueify.cue.WaitCue;
import com.cueify.gui.panel.cueeditor.CueEditorModel;
import com.cueify.gui.panel.cueeditor.tabs.audio.CueEditorAudio;
import com.cueify.gui.panel.cueeditor.tabs.basic.CueEditorBasics;
import com.cueify.gui.panel.cueeditor.tabs.wait.CueEditorWait;

public class TabGUIManager {
	
	public static Tab[] getTabsForModel(CueEditorModel ref) {
		return getAllTabsForGUIModel(ref);
	}

	private static Tab[] getAllTabsForGUIModel(CueEditorModel ref) {
		ArrayList<Tab> tabs = new ArrayList<Tab>();
		tabs.addAll(Arrays.asList(getDefaultCueTabs(ref)));
		tabs.addAll(Arrays.asList(getOtherTabs(ref)));
		return tabs.toArray(new Tab[tabs.size()]);
	}

	private static Tab[] getDefaultCueTabs(CueEditorModel ref) {
		if (ref.cueListItemToEdit == null) {
			return new Tab[0];
		}
		return new Tab[]{
				new CueEditorBasics(ref).getTab()
		};
	}

	private static Tab[] getOtherTabs(CueEditorModel ref) {
		Tab[] tab = new Tab[0];
		if (ref.cueListItemToEdit == null) {
			return tab;
		}
		Cue c = ref.cueListItemToEdit.getCue();
		if (AudioCue.class.isInstance(c)) {
			tab = new Tab[]{
					new CueEditorAudio(ref).getTab()
			};
		} else if (WaitCue.class.isInstance(c)) {
			tab = new Tab[]{
					new CueEditorWait(ref).getTab()
			};
		}
		return tab;
	}
}
