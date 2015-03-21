package com.cueify.output.graph.point;

public class PercentPointFactory {
	public static DomainPercentPoint createPercentDomainPoint(double x, double y, double y_out_of) {
		return new ImmutablePoint(x, y/y_out_of);
	}
	
	public static RangePercentPoint createPercentRangePoint(double x, double y, double x_out_of) {
		return new ImmutablePoint(x/x_out_of, y);
	}
	
	public static PurePercentPoint createPurePercentPoint(double x, double y, double x_out_of, double y_out_of) {
		return new ImmutablePoint(x/x_out_of, y/y_out_of);
	}
}