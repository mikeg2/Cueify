package com.cueify.output.graph;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import com.cueify.output.graph.point.Point;

public abstract class AddPointsGraph implements MutablePointGraph {
	private ObservableList<Point> points = FXCollections.<Point>synchronizedObservableList(FXCollections.<Point>observableArrayList());

	public Point[] getAllPoints() {
		return points.toArray(new Point[points.size()]);
	}

	@Override
	public synchronized void addPoint(Point p) {
		System.out.println("ADD");
		points.add(p);
	}

	@Override
	public synchronized void deletePoint(Point p) {
		boolean wasRemoved = points.remove(p);
		System.out.println(wasRemoved);
	}

	@Override
	public synchronized void replacePoint(Point replace, Point with) {
		System.out.println("REPLACE");
		deletePoint(replace);
		System.out.println("SIZE: " + points.size());
		addPoint(with);
		System.out.println("SIZEA: " + points.size());
	}
	
	@Override
	public ObservableList<Point> getPoints() {
		return FXCollections.unmodifiableObservableList(points);
	}
}
