package com.cueify.gui;

import java.io.File;

import com.cueify.gui.controlbar.ControlBar;
import com.cueify.gui.linker.TableTabLinker;
import com.cueify.gui.panel.addlist.AddList;
import com.cueify.gui.panel.cueeditor.CueEditor;
import com.cueify.gui.panel.cuetable.CueTable;
import com.cueify.save.Project;
import com.sun.javafx.geom.BaseBounds;
import com.sun.javafx.geom.transform.BaseTransform;
import com.sun.javafx.jmx.MXNodeAlgorithm;
import com.sun.javafx.jmx.MXNodeAlgorithmContext;
import com.sun.javafx.sg.PGNode;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class ProjectPane extends BorderPane {
	private Project project;
	private BorderPane mainInterface = new BorderPane();
	private SplitPane interfaceDevider = new SplitPane();
	private CueEditor editor = new CueEditor();
	private CueTable centerTable = new CueTable();
	private ControlBar controlBar;
	
	public ProjectPane(Project project) {
		setProject(project);
		mainInterface.setLeft(new AddList());
		mainInterface.setCenter(centerTable);
		mainInterface.setBottom(editor);
		mainInterface.setTop(controlBar);
		TableTabLinker.link(centerTable, editor);
		setCenter(mainInterface);
	}

	private void setProject(Project project) {
		this.project = project;
		setUp();
	}

	private void setUp() {
		setUpMainInterface();
		setUpEditor();
		setUpControlBar();
	}

	private void setUpControlBar() {
		if (controlBar == null) {
			controlBar = new ControlBar(centerTable, project.getCueList());
		} else {
			controlBar.setTableAndList(centerTable, project.getCueList());
		}
	}

	private void setUpEditor() {
		editor.setCue(null);
	}

	private void setUpMainInterface() {
		setUpCueTable();
	}

	private void setUpCueTable() {
		centerTable.setCueList(project.getCueList());
	}

}
