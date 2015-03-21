package com.cueify.gui.panel.addlist.cuegui;

import java.net.URI;

import com.cueify.cue.AudioCue;
import com.cueify.cue.Cue;
import com.cueify.cue.WaitCue;

public class ListWaitCueGUI implements ListCueGUI {

	@Override
	public Cue createCue() {
		Cue c = new WaitCue();
		c.setName("Untitled Wait Cue");
		return c;
	}

	@Override
	public URI getPathToImage() {
		return null;
	}

	@Override
	public String getTitle() {
		return "Wait Cue";
	}
	
	@Override
	public String toString() {
		return getTitle();
	}

}
