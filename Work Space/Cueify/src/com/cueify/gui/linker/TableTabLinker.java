package com.cueify.gui.linker;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.gui.panel.cueeditor.CueEditor;
import com.cueify.gui.panel.cuetable.CueTable;
import com.cueify.cuelist.*;

public class TableTabLinker {
	
	public static void link(CueTable table, final CueEditor editor) {
		table.reorderableCueTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<CueListItem>() {

			@Override
			public void changed(ObservableValue<? extends CueListItem> observable,
					CueListItem oldValue, CueListItem newValue) {
				if (newValue == null) {
					//TODO add "NO CUE SELECTED" MSG
				} else {
					editor.setCue(newValue);
				}
			}
		});
	}
}
