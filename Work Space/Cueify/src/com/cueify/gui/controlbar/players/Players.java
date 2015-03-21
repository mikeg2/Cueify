package com.cueify.gui.controlbar.players;

import java.net.URL;

import com.cueify.cuelist.CueList;
import com.cueify.gui.panel.GUIPanelOrganizer;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.ViewModelDeleget;
import com.cueify.gui.panel.cuetable.CueTable;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

public class Players extends GridPane implements ViewModelDeleget<PlayersModel, Players> {
	@FXML Button playButton;
	@FXML Button pauseButton;
	@FXML Button stopButton;
	@FXML Button stopAllButton;
	final URL FXML_LOCATION = getClass().getResource("PlayersFXML.fxml");

	public Players(CueTable cueTable, CueList cueList) {
		PlayersModel model =  new PlayersModel();
		model.cueList = cueList;
		model.cueTable = cueTable;
		new GUIPanelOrganizer<PlayersModel, Players>(this, FXML_LOCATION, model, this);
	}

	@Override
	public GUIViewModel<PlayersModel, Players> createViewModel() {
		return new PlayersViewModel();
	}
}