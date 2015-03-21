package com.cueify.output.graph;

import com.cueify.output.graph.point.Point;

import javafx.collections.ObservableList;

public interface PointGraph extends Graph {
	public ObservableList<Point> getPoints();
}
