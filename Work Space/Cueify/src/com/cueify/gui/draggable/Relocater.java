package com.cueify.gui.draggable;

import com.cueify.output.graph.point.Point;

import javafx.scene.Node;

public interface Relocater {
	public void relocate(Node c, Point to);
	public Point getLocation(Node c);
}
