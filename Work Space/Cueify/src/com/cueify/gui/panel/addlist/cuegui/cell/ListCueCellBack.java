package com.cueify.gui.panel.addlist.cuegui.cell;

import java.net.URL;

import com.cueify.gui.panel.GUIPanelOrganizer;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.ViewModelDeleget;
import com.cueify.gui.panel.addlist.cuegui.ListCueGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

public class ListCueCellBack extends GridPane implements ViewModelDeleget<ListCueCellModel, ListCueCellBack> {
	final URL FXML_LOCATION = getClass().getResource("ListCueCellFXML.fxml");
	@FXML public ImageView icon;
	@FXML public Label typeLabel;

	public ListCueCellBack(ListCueGUI gui) {
		ListCueCellModel ref = new ListCueCellModel();
		ref.gui = gui;
		new GUIPanelOrganizer<ListCueCellModel, ListCueCellBack>(this, FXML_LOCATION, ref, this);
	}

	@Override
	public GUIViewModel<ListCueCellModel, ListCueCellBack> createViewModel() {
		return new ListCueCellViewModel();
	}
}
