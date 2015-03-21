package com.cueify.gui.graph.display;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.cueify.output.graph.MutablePointGraph;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public class LinearGraphDisplay extends MutablePointGraphDisplay implements MutableVisualGraph {
	private Canvas lineCanvas;
	private Color lineColor = Color.GREEN;
	private Color axisColor = Color.BLUE;
	private double lineWidth = 5;

	public LinearGraphDisplay(MutablePointGraph g) {
		super(g);
		lineCanvas = new Canvas();
		linkCanvasToSize(lineCanvas);
		this.getChildren().add(lineCanvas);
	}

	@Override
	public void drawLines() {
		synchronized (getGraph()) {
			Point[] point = getImportantGraphPoints(getMutableGraph());
			lineCanvas.getGraphicsContext2D().clearRect(0, 0, this.getWidth(), this.getHeight());
			connectPoints(point);
		}
	}

	private void connectPoints(Point[] point) {
		Point[] ordered = orderPointsByX(point);
		System.out.println(Arrays.toString(ordered));
		for (int i = 0; i < ordered.length-1; i++) {
			drawLineForPoints(ordered[i], ordered[i+1]);
		}
	}
	

	private void drawLineForPoints(Point graphPoint1, Point graphPoint2) {
		Point display1 = convertGraphPointToDisplayPoint(graphPoint1);
		Point display2 = convertGraphPointToDisplayPoint(graphPoint2);
		GraphicsContext gc = lineCanvas.getGraphicsContext2D();
		gc.setStroke(getLineColor());
		gc.setLineWidth(getLineWidth());
		gc.strokeLine(display1.getX(), display1.getY(), display2.getX(), display2.getY());
		System.out.println("Stroked Lines From " + display1 + " to: " + display2 + " with: " + gc.getLineWidth());
	}

	private Point[] orderPointsByX(Point[] point) {
		Point[] pointC = point.clone();
		Arrays.sort(pointC, new Comparator<Point>() {

			@Override
			public int compare(Point o1, Point o2) {
				if (o1.getX() == o2.getX()) {
					return 0;
				}
				boolean isGreater = o1.getX() > o2.getX();
				if (isGreater) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		return pointC;
	}

	private Point[] getImportantGraphPoints(MutablePointGraph graph) {
		ArrayList<Point> points = new ArrayList<>();
		points.addAll(graph.getPoints());
		if (!isPointAtX(getXrange().getStart(), graph)) {
			points.add(new ImmutablePoint(getXrange().getStart(), 0));
		}
		if (!isPointAtX(getXrange().getEnd(), graph)) {
			points.add(new ImmutablePoint(getXrange().getEnd(), 0));
		}
		return points.toArray(new Point[points.size()]);
	}

	private boolean isPointAtX(double x, MutablePointGraph g) {
		for (Point p : g.getPoints()) {
			if (p.getX() == x) {
				return true;
			}
		}
		return false;
	}
	
	
	//Mutable Visual
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
