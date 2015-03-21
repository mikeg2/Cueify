package com.cueify.gui.panel.addlist.cuegui;

public class ListCueGUIs {
	public static ListCueGUI[] getCueGUIs() {
		ListCueGUI[] gui = new ListCueGUI[2];
		gui[0] = new ListAudioCueGUI();
		gui[1] = new ListWaitCueGUI();
		return gui;
	}
}
