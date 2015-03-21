package com.cueify.output.graph;

public class Range {
	public static final Range MAX_RANGE = new Range(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
	private double start;
	private double end;

	/**
	 * Creates the immutable range from two numbers.
	 * It does not matter what order they are in.
	 * The larger one will be the end and the smaller, the start.
	 * @param one One of the bounds
	 * @param two The other bound
	 */
	public Range(double one, double two) {
		this.start = one < two ? one : two;
		this.end = one > two ? one : two;
	}
	
	/**
	 * Creates a range from a single point either to Double.MAX_VALUE or Double.MIN_VALUE.
	 * @param start If true, the second bound is Double.MAX_VALUE. If false, the second bound in Double.MIN_VALUE
	 * @param singleValue The single defined bound.
	 */
	public Range(boolean start, double singleValue) {
		this.start = start ? singleValue : Double.NEGATIVE_INFINITY;
		this.end = start ? Double.POSITIVE_INFINITY : singleValue;
	}
	
	public double getStart() {
		return start;
	}
	
	public double getEnd() {
		return end;
	}
	
	public boolean isNumberWithin(double number) {
		return number >= start && number <= end;
	}

	public double getLength() {
		return end-start;
	}
	
	public static double convertPointFromRangeToRange(double d, Range from, Range to) {
//		return (to.getLength()*(d))/(from.getLength());
		double percentThroughF = (d - from.getStart())/(from.getLength());
		double percentPlace = to.getLength()*percentThroughF + to.getStart();
		return percentPlace;
	}
}
