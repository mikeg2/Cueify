package com.cueify.extension.helper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.TableView;
import javafx.scene.layout.Pane;

public class BareTable<T> extends TableView<T> {
	public BareTable() {
		removeHeader();
		//removeScrollBar();
	}

	private void removeScrollBar() {
		 final ScrollBar  scrollBarH = (ScrollBar) lookup(".scroll-bar:hotizontal");
	     scrollBarH.setVisible(false);		
	}

	private void removeHeader() {
		widthProperty().addListener(new ChangeListener<Number>() {
	        @Override
	        public void changed(ObservableValue<? extends Number> ov, Number t, Number t1) {
	            // Get the table header
	            Pane header = (Pane)lookup("TableHeaderRow");
	            if(header!=null && header.isVisible()) {
	              header.setMaxHeight(0);
	              header.setMinHeight(0);
	              header.setPrefHeight(0);
	              header.setVisible(false);
	              header.setManaged(false);
	            }
	        }
	    });		
	}
}
