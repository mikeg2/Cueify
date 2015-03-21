package com.cueify.output.property.link;

import com.cueify.output.graph.SingleXYPointGraph;

import javafx.beans.value.WritableValue;

public class NumberPropertyGraphLink implements PropertyGraphLink<WritableValue<Number>>{
	private WritableValue<Number> prop;
	private SingleXYPointGraph graph;

	@Override
	public WritableValue<Number> getProperty() {
		return prop;
	}

	@Override
	public SingleXYPointGraph getGraph() {
		return graph;
	}

	@Override
	public void setProperty(WritableValue<Number> property) {
		prop = property;
	}

	@Override
	public void setGraph(SingleXYPointGraph graph) {
		this.graph = graph;
	}

	@Override
	public void setToPoint(double percent) {
		double value = graph.getSinglePointAtX(percent).getY();
		prop.setValue(value);
	}

}