package com.cueify.gui.panel.addlist.cuegui.cell;

import com.cueify.gui.panel.addlist.cuegui.ListCueGUI;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.ListCell;

public class ListCueCell extends ListCell<ListCueGUI> {
	@Override
	public void updateItem(ListCueGUI gui, boolean empty){
		if (empty) {
			return;
		}
	    super.updateItem(gui,empty);
	    setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
	    setGraphic(new ListCueCellBack(gui));
	}
}
