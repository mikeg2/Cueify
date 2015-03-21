package com.cueify.gui.graph.display;

import com.cueify.output.graph.Graph;
import com.cueify.output.graph.function.LinearBasicYXPointGraph;

public class GraphDisplayFactory {
	public static GraphDisplay createDisplayForGraph(Graph g) {
		if (LinearBasicYXPointGraph.class.isInstance(g)) {
			return new LinearGraphDisplay((LinearBasicYXPointGraph) g);
		} else {
			return new BasicGraphDisplay(g);
		}
	}
}
