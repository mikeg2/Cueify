package com.cueify.gui.menubar;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;

import com.cueify.gui.panel.addlist.cuegui.ListCueGUI;
import com.cueify.gui.panel.addlist.cuegui.ListCueGUIs;

public class MenuBarModel {
	public static ObservableList<ListCueGUI> cueGUIs = FXCollections.observableArrayList(ListCueGUIs.getCueGUIs());
}
