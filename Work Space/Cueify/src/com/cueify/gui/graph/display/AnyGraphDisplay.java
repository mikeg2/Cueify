package com.cueify.gui.graph.display;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

import com.cueify.output.graph.Graph;
import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.Point;

public class AnyGraphDisplay extends AnchorPane implements GraphDisplay {
	GraphDisplay thisGraphDisplay = null;
	
	public AnyGraphDisplay(Graph g) {
		thisGraphDisplay = GraphDisplayFactory.createDisplayForGraph(g);
	}
	
	public void changeGraphTo(Graph g) {
		getChildren().remove(thisGraphDisplay.getNode());
		thisGraphDisplay = GraphDisplayFactory.createDisplayForGraph(g);
		AnchorPane.setLeftAnchor(thisGraphDisplay.getNode(), 0.0);
		AnchorPane.setRightAnchor(thisGraphDisplay.getNode(), 0.0);
		AnchorPane.setTopAnchor(thisGraphDisplay.getNode(), 0.0);
		AnchorPane.setBottomAnchor(thisGraphDisplay.getNode(), 0.0);
		getChildren().add(thisGraphDisplay.getNode());
		drawGraph();
	}

	//Forwarded methods
	@Override
	public Point convertGraphPointToDisplayPoint(Point p) {
		return thisGraphDisplay.convertGraphPointToDisplayPoint(p);
	}

	@Override
	public Point convertDisplayPointToGraphPoint(Point p) {
		return thisGraphDisplay.convertDisplayPointToGraphPoint(p);
	}

	@Override
	public Point[] getDisplayPointsAt(double displayX) {
		return thisGraphDisplay.getDisplayPointsAt(displayX);
	}

	@Override
	public Graph getGraph() {
		return thisGraphDisplay.getGraph();
	}

	@Override
	public Range getXRange() {
		return thisGraphDisplay.getXRange();
	}

	@Override
	public Range getYRange() {
		return thisGraphDisplay.getYRange();
	}

	@Override
	public void drawGraph() {
		thisGraphDisplay.drawGraph();
	}

	@Override
	public void drawLines() {
		thisGraphDisplay.drawLines();
	}

	@Override
	public Node getNode() {
		return thisGraphDisplay.getNode();
	}

}
