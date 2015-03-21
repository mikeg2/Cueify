package com.cueify.extension.reorder;

import com.cueify.extension.LocalDragboard;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.TableRow;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;

public class ReorderableTableRow<T> extends TableRow<T> {

	public ReorderableTableRow() {
		itemProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				System.out.println("ITEM: " + getItem());
			}
		});
		setOnDragDetected(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (getItem() == null) {
					return;
				}
				
				Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
				ClipboardContent content = new ClipboardContent();
				content.putString(getItem().toString());
				dragboard.setContent(content);
				LocalDragboard.getInstance().putValue(getItem().getClass(), getItem());
				event.consume();
			}
		});
		
		setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (getItem() == null) {
					return;
				}
				if (event.getGestureSource() != this
						&& LocalDragboard.getInstance().hasType(getItem().getClass())) {
					event.acceptTransferModes(TransferMode.MOVE);
				}
				event.consume();
			}
		});
		
		setOnDragEntered(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (getItem() == null) {
					return;
				}
				if (event.getGestureSource() != this
						&& LocalDragboard.getInstance().hasType(getItem().getClass())) {
	                Platform.runLater(new Runnable() {

						@Override
						public void run() {
							setOpacity(.3);							
						}
	                	
	                });
				}
				event.consume();
			}
		});
		
		setOnDragExited(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (getItem() == null) {
					return;
				}
				if (event.getGestureSource() != this
						&& LocalDragboard.getInstance().hasType(getItem().getClass())) {
	                Platform.runLater(new Runnable() {

						@Override
						public void run() {
							setOpacity(1.0);							
						}
	                	
	                });
				}
				event.consume();
			}
		});
		
		setOnDragDropped(new EventHandler<DragEvent>() {

			@Override
			public synchronized void handle(DragEvent event) {
				if (getTableView().getItems().size() > getIndex()) {
					return;
				} else if (getItem() == null) {
					return;
				}
				System.out.println("INDEX: " + getIndex());
				@SuppressWarnings("unchecked")
				T item = LocalDragboard.getInstance().<T>getValue((Class<T>)getItem().getClass());
                final ObservableList<T> list = FXCollections.observableArrayList(getTableView().getItems());
                list.remove(item);
                list.add(getIndex(), item);
                Platform.runLater(new Runnable() {
					
					@Override
					public void run() {
		                getTableView().setItems(list);
		                getTableView().getColumns().get(0).setVisible(false);
		                getTableView().getColumns().get(0).setVisible(true);
		                getTableView().getSelectionModel().clearSelection();
					}
				});
				event.consume();
			}
		});
	}
}
