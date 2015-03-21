package com.cueify.output.graph.point;


public class ImmutablePoint implements Point, RangePercentPoint, DomainPercentPoint,
		PurePercentPoint {
	private double x;
	private double y;
	
	public ImmutablePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public double getX() {
		return x;
	}

	@Override
	public double getY() {
		return y;
	}

	@Override
	public boolean equals(Object p) {
		if (p == this) {
			return true;
		} else if (Point.class.isInstance(p)) {
			return equals_point((Point) p); 
		}
		return false;
	}

	private boolean equals_point(Point p) {
		boolean does_equal_x = this.getX() == p.getX();
		boolean does_equal_y = this.getY() == p.getY();
		return does_equal_x && does_equal_y;
	}

	@Override
	public double getXOutOf(double x_out_of) {
		return getX() * x_out_of;
	}

	@Override
	public double getYOutOf(double y_out_of) {
		return getY() * y_out_of;
	}
	
	@Override
	public String toString() {
		return "X: " + x + " Y: " + y;
	}
}
