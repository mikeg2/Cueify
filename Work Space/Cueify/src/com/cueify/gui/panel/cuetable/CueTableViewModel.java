package com.cueify.gui.panel.cuetable;

import com.cueify.cue.Cue;
import com.cueify.cuelist.CueListItem;
import com.cueify.extension.LocalDragboard;
import com.cueify.gui.panel.BasicGUIViewModel;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.cuetable.callback.CueContentCallback;
import com.cueify.gui.panel.cuetable.callback.CueNameCallback;
import com.cueify.gui.panel.cuetable.callback.CueNumberCallback;
import com.cueify.gui.panel.cuetable.callback.CuePostWaitCallback;
import com.cueify.gui.panel.cuetable.callback.CuePreWaitCallback;
import com.cueify.gui.panel.cuetable.callback.CueTargetCallback;
import com.cueify.timeline.TimelineState;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.DragEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.TransferMode;

/**
 * CueTableController acts as the controller for the fxml.
 * It delegates the task of the model to a model class.
 * Though it would be better for the Controller class
 * not to know about the model, the way FXML is structured
 * would make this very hard.
 * 
 * CueTableController only is the controller for the table
 * in the application.
 * @author Mike
 *
 */
//TODO Refactor and divide
public class CueTableViewModel extends BasicGUIViewModel<CueTableModel, CueTable> {


	@Override
	public void linkControllerToView(final CueTable panel) {
		linkToModel(panel);
		setUpDelete(panel);
		setUpAutoIncriment(panel);
		setUpSpacePlay(panel);
		setUpDragDrop(panel);
		setUpCopyAndPaste(panel);
	}

	private void linkToModel(CueTable panel) {
		bindItemsToTable(panel);
		bindNameToColumn(panel);
		bindNumberToColumn(panel);
		bindTargetToColumn(panel);
		bindPreWaitToColumn(panel);
		bindPostWaitToColumn(panel);
		bindContentPointToColumn(panel);		
	}
	

	private void setUpDelete(final CueTable panel) {

		panel.reorderableCueTable.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.isShortcutDown() && KeyCode.BACK_SPACE == event.getCode()) {
					Cue selected = panel.getSelectedCue();
					getModel().list.remove(selected);
				}
			}
		});	
	}

	private void bindItemsToTable(final CueTable view) {
		view.reorderableCueTable.setList(getModel().list);
	}
	
	private void bindNameToColumn(CueTable view) {
		view.nameColumn.setCellValueFactory(new CueNameCallback());
	}
	
	private void bindNumberToColumn(CueTable view) {
		view.numberColumn.setCellValueFactory(new CueNumberCallback());
	}

	private void bindTargetToColumn(CueTable view) {
		view.targetColumn.setCellValueFactory(new CueTargetCallback());
	}

	private void bindPreWaitToColumn(CueTable view) {
		view.preWaitColumn.setCellValueFactory(new CuePreWaitCallback());
	}
	
	private void bindPostWaitToColumn(CueTable view) {
		view.postWaitColumn.setCellValueFactory(new CuePostWaitCallback());
	}
	
	private void bindContentPointToColumn(CueTable view) {
		view.contentColumn.setCellValueFactory(new CueContentCallback());
	}

	private void setUpAutoIncriment(final Node panel) {
		final ChangeListener<? super TimelineState> playListener = new ChangeListener<TimelineState>() {

			@Override
			public void changed(
					ObservableValue<? extends TimelineState> observable,
					TimelineState oldValue, TimelineState newValue) {
				if (newValue == TimelineState.PLAY) {
					 incrimentSelection(panel);
					 observable.removeListener(this);
				}
			}
		};
		ChangeListener<? super CueListItem> selectListener = new ChangeListener<CueListItem>() {

			@Override
			public void changed(
					ObservableValue<? extends CueListItem> observable,
					CueListItem oldValue, CueListItem newValue) {
				if(oldValue != null)
					oldValue.getCue().getCueTimeline().state().removeListener(playListener);
				if(newValue != null)
					newValue.getCue().getCueTimeline().state().addListener(playListener);
			}
		};
		((CueTable)panel).reorderableCueTable.getSelectionModel().selectedItemProperty().addListener(selectListener);
	}

	private void setUpSpacePlay(final CueTable panel) {
		((CueTable)panel).reorderableCueTable.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getText().equals(" ")) {
					playSelected(panel);
				}
			}
		});		
	}

	protected void incrimentSelection(Node panel) {
		CueTable table = ((CueTable) panel);
		int index = table.reorderableCueTable.getSelectionModel().getSelectedIndex();
		int newIndex = index > table.getChildrenUnmodifiable().size()
					? index : index + 1;
		table.reorderableCueTable.getSelectionModel().select(newIndex);
	}

	protected void playSelected(CueTable panel) { 
		if (((CueTable) panel).reorderableCueTable.getSelectionModel().isEmpty()) {
			return;
		}
		System.out.println("GET TEXT: '" + "'");
		panel.getSelectedCue().getCueTimeline().play();	
	}

	private void setUpDragDrop(final Node panel) {
		panel.setOnDragOver(new EventHandler<DragEvent>() {

			@Override
			public void handle(DragEvent event) {
				if (!getModel().list.isEmpty()) {
					return;
				}

				if (LocalDragboard.getInstance().hasType(CueListItem.class)) {
					System.out.println("Drag In");
					((CueTable) panel).highlight();
					event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
				}
			}
		});
		panel.setOnDragDropped(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!getModel().list.isEmpty()) {
					return;
				}
				CueListItem c = LocalDragboard.getInstance().getValue(CueListItem.class);
				System.out.println(c);
				getModel().list.add(c);
				LocalDragboard.getInstance().clearAll();
				((CueTable) panel).deHighlight();
			}
		});
		panel.setOnDragExited(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if (!getModel().list.isEmpty()) {
					return;
				}
				((CueTable) panel).deHighlight();
			}
		});
	}
	

	private void setUpCopyAndPaste(CueTable panel) {
	}

}
