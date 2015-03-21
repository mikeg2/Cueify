package com.cueify.gui.controlbar;

import com.cueify.cuelist.CueList;
import com.cueify.gui.controlbar.players.Players;
import com.cueify.gui.panel.cuetable.CueTable;

import javafx.scene.layout.BorderPane;

public class ControlBar extends BorderPane {
	public ControlBar(CueTable cueTable, CueList cueList) {
		setTableAndList(cueTable, cueList);
	}

	public void setTableAndList(CueTable cueTable, CueList cueList) {
		Players p = new Players(cueTable, cueList);
		setLeft(p);		
	}
}