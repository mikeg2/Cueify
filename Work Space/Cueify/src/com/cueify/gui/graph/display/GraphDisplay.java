package com.cueify.gui.graph.display;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;

import com.cueify.output.graph.Graph;
import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.Point;

public interface GraphDisplay {
	public Point convertGraphPointToDisplayPoint(Point p);
	public Point convertDisplayPointToGraphPoint(Point p);
	public Point[] getDisplayPointsAt(double displayX);
	public Graph getGraph();
	public Range getXRange();
	public Range getYRange();
	public void drawGraph();
	public void drawLines();
	public javafx.scene.Node getNode();
	
	//Node realted
	public ReadOnlyDoubleProperty widthProperty();
	public ReadOnlyDoubleProperty heightProperty();
	public DoubleProperty maxWidthProperty();
	public DoubleProperty minWidthProperty();
	public DoubleProperty maxHeightProperty();
	public DoubleProperty minHeightProperty();
	public DoubleProperty prefWidthProperty();
	public DoubleProperty prefHeightProperty();
}
