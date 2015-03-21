package com.cueify.output.graph;

import com.cueify.output.graph.finite.FiniteDomainGraph;
import com.cueify.output.graph.finite.FiniteRangeGraph;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public class FlatGraph  implements SingleXYPointGraph, FiniteDomainGraph, FiniteRangeGraph{
	private double value;
	private final Range range;
	
	public FlatGraph(Range range) {
		this.range = range;
	}

	public FlatGraph() {
		this.range = Range.MAX_RANGE;
	}

	public FlatGraph(double value) {
		this();
		this.value = value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public Range getRange() {
		return range;
	}

	@Override
	public Range getDomain() {
		return Range.MAX_RANGE;
	}

	@Override
	public Point[] getPointsAtX(double x) {
		return new Point[]{getSinglePointAtX(x)};
	}

	@Override
	public Point getSinglePointAtX(double x) {
		return new ImmutablePoint(x, value);
	}
}
