package com.cueify.output.graph;

import javafx.collections.ObservableList;

import com.cueify.output.graph.finite.FiniteDomainGraph;
import com.cueify.output.graph.finite.FiniteRangeGraph;
import com.cueify.output.graph.point.Point;

public class BasicSingleYXPointGraph extends AddPointsGraph implements
		SingleXYPointGraph, FiniteDomainGraph, FiniteRangeGraph {

	private Range domain;
	private Range range;

	public BasicSingleYXPointGraph(Range domain, Range range) {
		this.domain = domain;
		this.range = range;
	}

	@Override
	public synchronized void addPoint(Point p) {
		ObservableList<Point> found = getPoints();
		for (Point point : found) {
			if (point.getX() == p.getX()) {
				deletePoint(p);
			}
		}
		if (fitsRangeAndDomain(p))
			super.addPoint(p);
	}

	private boolean fitsRangeAndDomain(Point p) {
		return domain.isNumberWithin(p.getX()) && range.isNumberWithin(p.getY());
	}

	@Override
	public Point[] getPointsAtX(double x) {
		return new Point[]{getSinglePointAtX(x)};
	}

	@Override
	public Range getRange() {
		return this.range;
	}

	@Override
	public Range getDomain() {
		return this.domain;
	}

	@Override
	public Point getSinglePointAtX(double x) {
		Point[] allPoints = getAllPoints();
		for(Point p : allPoints) {
			return p;
		}
		return null;
	}


}
