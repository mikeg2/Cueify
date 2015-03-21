package com.cueify.output.property.link;

import com.cueify.output.graph.Graph;
import com.cueify.output.graph.SingleXYPointGraph;

import javafx.beans.value.WritableValue;

public interface PropertyGraphLink<T extends WritableValue<?>> {
	public T getProperty();
	public Graph getGraph();
	public void setProperty(T property);
	public void setGraph(SingleXYPointGraph graph);
	public void setToPoint(double percent);
}