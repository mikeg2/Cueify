package com.cueify.gui.panel.cuetable.callback;

import com.cueify.cuelist.CueListItem;

import javafx.beans.binding.StringBinding;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class CueNameCallback implements Callback<TableColumn.CellDataFeatures<CueListItem,String>, ObservableValue<String>> {
	
	@Override
	public ObservableValue<String> call(final CellDataFeatures<CueListItem, String> param) {
		return new StringBinding() {
			{
				super.bind(param.getValue().getCue().name());
			}
			@Override
			protected String computeValue() {
				return param.getValue().getCue().getName();
			}
		};
	}

}
