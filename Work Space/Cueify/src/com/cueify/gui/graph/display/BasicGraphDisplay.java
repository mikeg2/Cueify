package com.cueify.gui.graph.display;

import com.cueify.output.graph.Graph;
import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

// Create factory with optimized line drawers. THIS IS WAYYY TOO SLOW
public class BasicGraphDisplay extends CommonGraphDisplay implements MutableVisualGraph {
	private static final double LINE_RESOLUTION = 3;


	private Color lineColor = Color.GREEN;
	private Color axisColor = Color.BLUE;
	private double lineWidth = 5;

	private Canvas mainCanvas = new Canvas();

	public BasicGraphDisplay(final Graph g) {
		super(g);
		this.getChildren().add(mainCanvas);
		linkCanvasToSize(mainCanvas);
	}

	public synchronized void drawGraph() {
		synchronized(getYrange()) {
			synchronized(getXrange()){
				draw0Line();
				synchronized (getGraph()) {
					drawLines();
				}
			}
		}
	}

	private void draw0Line() {
		if (getYrange().isNumberWithin(0)) {
			double y_pos = convertGraphPointToDisplayPoint(new ImmutablePoint(0, 0)).getY();
			GraphicsContext gc = mainCanvas.getGraphicsContext2D();
			gc.setStroke(axisColor);
			gc.strokeLine(0, y_pos, this.getWidth(), y_pos);
		}
	}


	public void drawLines() {
		double width = this.getWidth();
		synchronized(getGraph()) {
			System.out.println("---START CONNECT---");
			for (double i = 0; i < width; i+=LINE_RESOLUTION) {
				double point = Range.convertPointFromRangeToRange(
						i, new Range(0, width), getXrange());
				Point[] p = getGraph().getPointsAtX(point);
				drawPointsOnGraph(p);
			}
			System.out.println("---END CONNECT---");
		}
	}

	private void drawPointsOnGraph(Point[] p) {
		for (Point point : p) {
			System.out.println("DRAWING POINT: " + point.toString());
			drawPointOnGraph(point);
		}
	}

	private void drawPointOnGraph(Point p) {
		Point adj = convertGraphPointToDisplayPoint(p);
		GraphicsContext gc = mainCanvas.getGraphicsContext2D();
		gc.setFill(lineColor);
		gc.fillRect(adj.getX()-.5*lineWidth, adj.getY()-lineWidth, lineWidth, lineWidth);
	}
	
	// Setters and Getters
	public Color getLineColor() {
		return lineColor;
	}

	public void setLineColor(Color lineColor) {
		this.lineColor = lineColor;
	}

	public double getLineWidth() {
		return lineWidth;
	}

	public void setLineWidth(double lineWidth) {
		this.lineWidth = lineWidth;
	}
}