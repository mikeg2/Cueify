package com.cueify.gui.draggable;

import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class RelocaterFactory {
	private static final Relocater anchorRelocator = new Relocater() {

		@Override
		public void relocate(Node c, Point to) {
			AnchorPane.clearConstraints(c);;
			AnchorPane.setRightAnchor(c, 0.0);
			AnchorPane.setLeftAnchor(c, to.getX());
			AnchorPane.setTopAnchor(c, to.getY());
		}

		@Override
		public Point getLocation(Node c) {
			return new ImmutablePoint(AnchorPane.getLeftAnchor(c), AnchorPane.getTopAnchor(c));
		};
	};

	public static Relocater createAnchorRelocator() {
		return anchorRelocator;
	}
}
