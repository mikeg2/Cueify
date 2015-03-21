package com.cueify.gui.panel.cuetable;

import java.net.URL;

import com.cueify.cue.Cue;
import com.cueify.cuelist.CueList;
import com.cueify.cuelist.CueListItem;
import com.cueify.gui.panel.GUIPanelOrganizer;
import com.cueify.gui.panel.GUIViewCallback;
import com.cueify.gui.panel.GUIViewModel;
import com.cueify.gui.panel.ViewModelDeleget;
import com.cueify.gui.panel.cuetable.helper.ReorderableCueTable;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.layout.AnchorPane;
// TODO Fix bug where more than three digit decimals break the program
public class CueTable extends AnchorPane implements GUIViewCallback, ViewModelDeleget<CueTableModel, CueTable>{
	private final URL FXML_LOCATION = getClass().getResource("CueTableFXML.fxml");

	@FXML
	public ReorderableCueTable reorderableCueTable;
	
	@FXML
	public TableColumn<CueListItem, String> nameColumn;
	
	@FXML
	public TableColumn<CueListItem, String> numberColumn;

	@FXML
	public TableColumn<CueListItem, String> targetColumn;

	@FXML
	public TableColumn<CueListItem, String> preWaitColumn;

	@FXML
	public TableColumn<CueListItem, String> contentColumn;

	@FXML
	public TableColumn<CueListItem, String> postWaitColumn;
	
	private TableSelectionOverlay selectionOverlay = new TableSelectionOverlay(this);

	private GUIPanelOrganizer<CueTableModel, CueTable> organizer;

	public CueTable(CueList list) {
		CueTableModel ref = new CueTableModel();
		ref.list = list;
		this.organizer = new GUIPanelOrganizer<CueTableModel, CueTable>(this, FXML_LOCATION, ref, this);
	}

	public CueTable() {
		this(null);
	}

	public void highlight() {
		if (!getChildren().contains(selectionOverlay)) {
			getChildren().add(selectionOverlay);
			selectionOverlay.show();
		}
	}
	
	public void deHighlight() {
		getChildren().remove(selectionOverlay);
		selectionOverlay.hide();
	}

	@Override
	public void fxmlLoaded() {
		System.out.println("FXML LOADED");
	}
	
	public void setCueList(CueList list) {
		CueTableModel model = new CueTableModel();
		model.list = list;
		this.organizer.reOrganize(model);
	}

	@Override
	public GUIViewModel<CueTableModel, CueTable> createViewModel() {
		return new CueTableViewModel();
	}

	public Cue getSelectedCue() {
		if (this.reorderableCueTable.getSelectionModel().isEmpty()) {
			return null;
		} else if (this.reorderableCueTable.getSelectionModel().getSelectedItem() == null) {
			return null;
		}
		System.out.println("GET TEXT: '" + "'");
		return this.reorderableCueTable.getSelectionModel()
		.getSelectedItem().getCue();
	}
	
}