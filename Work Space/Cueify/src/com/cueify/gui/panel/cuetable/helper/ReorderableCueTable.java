package com.cueify.gui.panel.cuetable.helper;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.DragEvent;
import javafx.util.Callback;

import com.cueify.cue.Cue;
import com.cueify.cuelist.CueList;
import com.cueify.cuelist.CueListItem;
import com.cueify.extension.LocalDragboard;
import com.cueify.extension.reorder.ReorderableTable;


public class ReorderableCueTable extends ReorderableTable<CueListItem> {
	private CueList list;

	public ReorderableCueTable(CueList cueList) {
		list = cueList;
		updateListLink();
		makeReorderableCueTable(this);
	}
	
	public ReorderableCueTable() {
		makeReorderableCueTable(this);
	}
	
	private void updateListLink() {
		list.addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				setItems(list);
			}
		});
	}

	private void makeReorderableCueTable(TableView<CueListItem> tableView) {
		ReorderableTable.<CueListItem>makeReorderTable(tableView);
		final Callback<TableView<CueListItem>, TableRow<CueListItem>> oldFactory = tableView.getRowFactory();
		tableView.setRowFactory(new Callback<TableView<CueListItem>, TableRow<CueListItem>>() {

			@Override
			public TableRow<CueListItem> call(TableView<CueListItem> param) {
				final TableRow<CueListItem> row = oldFactory.call(param);
				row.setOnDragDropped(new EventHandler<DragEvent>() {

					@Override
					public void handle(DragEvent event) {
						System.out.println("CUE");
						CueListItem item = LocalDragboard.getInstance().<CueListItem>getValue(CueListItem.class);
		                list.remove(item.getCue());
		                list.add( row.getIndex(), item);
					}
				});
				return row;
			}
			
		});
	}
	
	//Model
	public CueList getList() {
		return list;
	}

	public void setList(CueList list) {
		if (list == null) {
			return;
		}
		this.list = list;
		getItems().setAll(list);
		updateListLink();
	}
}
