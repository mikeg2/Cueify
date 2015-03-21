package com.cueify.gui.panel.addlist.cuegui;

import java.net.URI;

import com.cueify.cue.AudioCue;
import com.cueify.cue.Cue;

public class ListAudioCueGUI implements ListCueGUI {

	@Override
	public Cue createCue() {
		Cue c = new AudioCue();
		c.setName("Untitled Audio Cue");
		return c;
	}

	@Override
	public URI getPathToImage() {
		return null;
	}

	@Override
	public String getTitle() {
		return "Audio Cue";
	}

	@Override
	public String toString() {
		return getTitle();
	}
}
