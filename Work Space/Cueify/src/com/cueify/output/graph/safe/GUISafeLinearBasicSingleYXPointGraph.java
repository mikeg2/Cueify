package com.cueify.output.graph.safe;

import com.cueify.output.graph.Range;
import com.cueify.output.graph.function.LinearBasicYXPointGraph;
import com.cueify.output.graph.point.Point;

public class GUISafeLinearBasicSingleYXPointGraph extends LinearBasicYXPointGraph {

	public GUISafeLinearBasicSingleYXPointGraph(Range domain, Range range) {
		super(domain, range);
	}

	@Override
	public void addPoint(final Point p) {

		javafx.application.Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				synchronized (this) {
					GUISafeLinearBasicSingleYXPointGraph.super.addPoint(p);
				}
			}
		});
	}
	
	@Override
	public void deletePoint(final Point p) {
		javafx.application.Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				synchronized (this) {
					GUISafeLinearBasicSingleYXPointGraph.super.deletePoint(p);
				}
			}
		});
	}
}
