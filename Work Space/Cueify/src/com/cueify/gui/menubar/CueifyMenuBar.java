package com.cueify.gui.menubar;

import java.net.URL;

import com.cueify.cue.Cue;
import com.cueify.extension.Unselectable;
import com.cueify.gui.panel.GUIPanelOrganizer;
import com.cueify.gui.panel.GUIViewCallback;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.ViewModelDeleget;
import com.cueify.gui.panel.addlist.cuegui.ListCueGUI;
import com.cueify.gui.panel.addlist.cuegui.cell.ListCueCell;

import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.util.Callback;

public class CueifyMenuBar extends javafx.scene.control.MenuBar implements ViewModelDeleget<MenuBarModel, CueifyMenuBar>, GUIViewCallback{
	final URL FXML_LOCATION = getClass().getResource("MenuBarFXML.fxml");
	@FXML MenuItem saveMenuItem;
	@FXML MenuItem openMenuItem;
	@FXML MenuItem newProjectMenuItem;
	@FXML MenuItem deleteCueMenuItem;
	@FXML MenuItem copyMenuItem;
	@FXML MenuItem pasteMenuItem;

	public CueifyMenuBar() {
		MenuBarModel model =  new MenuBarModel();
		new GUIPanelOrganizer<MenuBarModel, CueifyMenuBar>(this, FXML_LOCATION, model, this, this);
	}

	@Override
	public GUIViewModel<MenuBarModel, CueifyMenuBar> createViewModel() {
		return new MenuBarViewModel();
	}

	@Override
	public void fxmlLoaded() {
		setUseSystemMenuBar(true);
		saveMenuItem.setAccelerator(new KeyCodeCombinatio);
	}


}
