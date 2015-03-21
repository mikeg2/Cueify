package com.cueify.output.graph.function;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public class SlopeInOutFunctionGraph implements FunctionGraph {
	private enum Stage {IN, AT, OUT};
	private DoubleProperty inStart = new SimpleDoubleProperty(.2);
	private DoubleProperty outStart = new SimpleDoubleProperty(.2);
	private DoubleProperty valueTo = new SimpleDoubleProperty(1.0);
	private final Range domain;
	private final Range range;

	public SlopeInOutFunctionGraph(Range domain, Range range) {
		this.domain = domain;
		this.range = range;
	}

	@Override
	public Point getSinglePointAtX(double x) {
		Stage point = getStageAtPoint(x);
		switch (point) {
			case AT:
				return new ImmutablePoint(x, getValueTo());
			case OUT:
				return new ImmutablePoint(x, interpolateOut(x));
			case IN:
				return new ImmutablePoint(x, interpolateIn(x));
			default:
				break;
		}
		return null;
	}

	private double interpolateOut(double x) {
		double x1 = getOutStart();
		double y1 = getValueTo();
		double x3 = domain.getEnd();
		double y3 = 0;
		double result = Interpolator.linearInterpolate(x, x1, y1, x3, y3);
		System.out.println(x);
		return result;
	}

	private double interpolateIn(double x) {
		double y1 = 0;
		double x1 = 0;
		double y3 = getValueTo();
		double x3 = getInStart();
		return Interpolator.linearInterpolate(x, x1, y1, x3, y3);
	}

	public Stage getStageAtPoint(double x) {
		if(x < getInStart()) {
			return Stage.IN;
		} else if (x > getOutStart()) {
			return Stage.OUT;
		} else {
			return Stage.AT;
		}
	}

	@Override
	public Point[] getPointsAtX(double x) {
		return new Point[]{getSinglePointAtX(x)};
	}

	@Override
	public Range getDomain() {
		return domain;
	}

	@Override
	public Range getRange() {
		return range;
	}

	
	// Getters and Setter
	public Double getInStart() {
		return inStart.getValue();
	}
	
	public void setInStart(Double value) {
		inStart.setValue(value);
	}
	
	public DoubleProperty inStart() {
		return inStart;
	}
	
	public double getOutStart() {
		return outStart.getValue();
	}

	public void setOutStart(double value) {
		outStart.setValue(value);
	}

	public DoubleProperty outStart() {
		return outStart;
	}
	
	public double getValueTo() {
		return valueTo.getValue();
	}

	public void setValueTo(double value) {
		valueTo.setValue(value);
	}

	public DoubleProperty valueTo() {
		return valueTo;
	}

}
