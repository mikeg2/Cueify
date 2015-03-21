package com.cueify.gui.graph.display;

import javafx.scene.paint.Color;

public interface MutableVisualGraph extends GraphDisplay {
	public Color getLineColor();
	public void setLineColor(Color lineColor);
	public double getLineWidth();
	public void setLineWidth(double lineWidth);
}
