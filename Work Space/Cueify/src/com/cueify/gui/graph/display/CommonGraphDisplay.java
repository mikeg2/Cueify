package com.cueify.gui.graph.display;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;

import com.cueify.gui.graph.display.helper.GraphBackground;
import com.cueify.output.graph.Graph;
import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public abstract class CommonGraphDisplay extends AnchorPane implements GraphDisplay {
	private Range y_range = new Range(-1.0, 1.0);
	private Range x_range = new Range(0.0, 1.0);
	private Canvas backgroundCanvas = new GraphBackground();
	private Graph graph;
	
	public CommonGraphDisplay(Graph graph) {
		this.graph = graph;
		linkCanvasToSize(backgroundCanvas);
		getChildren().add(backgroundCanvas);
		addRedrawListeners();
	}

	private void addRedrawListeners() {
		this.widthProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				drawGraph(); //TODO make thread safe
			}
		});
		this.heightProperty().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				drawGraph();
			}
		});		
	}

	@Override
	public Point convertGraphPointToDisplayPoint(Point p) {
		if (p.getX() == 0.0) {
			System.out.println("Width: " + this.getWidth() + " Height: " + this.getHeight());
			System.out.println("Point:" + p);
		}
		double x_cord = Range.convertPointFromRangeToRange(
				p.getX(), x_range, new Range(0, getWidth()));
		double y_cord = this.getHeight() - Range.convertPointFromRangeToRange(
				p.getY(), y_range, new Range(0, getHeight()));
		System.out.println("Result: " + new ImmutablePoint(x_cord, y_cord));
		return new ImmutablePoint(x_cord, y_cord);
	}
	
	@Override
	public Point convertDisplayPointToGraphPoint(Point p) {
		double x_cord = Range.convertPointFromRangeToRange(
				p.getX(), new Range(0, getWidth()), x_range);
		double y_cord = this.getHeight() - Range.convertPointFromRangeToRange(
				p.getY(), new Range(0, getHeight()), y_range);
		return new ImmutablePoint(x_cord, y_cord);
	}

	@Override
	public Point[] getDisplayPointsAt(double displayX) {
		double graphX = convertDisplayPointToGraphPoint(new ImmutablePoint(displayX, 0)).getX();
		Point[] points = graph.getPointsAtX(graphX);
		for (int i = 0; i < points.length; i++) {
			points[i] = convertGraphPointToDisplayPoint(points[i]);
		}
		return points;
	}

	@Override
	public Graph getGraph() {
		return graph;
	}

	@Override
	public Range getXRange() {
		return x_range;
	}

	@Override
	public Range getYRange() {
		return y_range;
	}
	
	@Override
	public Node getNode() {
		return this;
	}
	
	// Common Methods
	protected void linkCanvasToSize(Canvas c) {
		c.widthProperty().bind(widthProperty());
		c.heightProperty().bind(heightProperty());
	}
	
	
	//Setters and Getters
	public Range getYrange() {
		return y_range;
	}

	public void setYrange(Range y_range) {
		this.y_range = y_range;
	}

	public Range getXrange() {
		return x_range;
	}

	public void setXrange(Range x_range) {
		this.x_range = x_range;
	}
}
