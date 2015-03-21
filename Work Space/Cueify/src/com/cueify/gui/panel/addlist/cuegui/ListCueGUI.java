package com.cueify.gui.panel.addlist.cuegui;

import java.net.URI;

import com.cueify.cue.Cue;

/**
 * Defines the interface/text for a basic 
 * @author Mike
 *
 */
public interface ListCueGUI {
	public abstract Cue createCue();
	public abstract URI getPathToImage();
	public abstract String getTitle();
}
