package com.cueify.extension.reorder;

import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class ReorderableTable<S> extends TableView<S> {

	public static <S> void makeReorderTable(TableView<S> table) {
		table.setRowFactory(new Callback<TableView<S>,TableRow<S>>() {

			@Override
			public TableRow<S> call(TableView<S> arg0) {
				TableRow<S> row = new ReorderableTableRow<>();
				return row;
			}
			
		});
	}
	
	public ReorderableTable() {
		ReorderableTable.<S>makeReorderTable(this);
	}

}
