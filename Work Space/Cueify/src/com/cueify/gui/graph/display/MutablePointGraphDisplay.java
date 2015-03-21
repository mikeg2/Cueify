package com.cueify.gui.graph.display;

import java.util.ArrayList;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import com.cueify.gui.draggable.DraggableParent;
import com.cueify.gui.draggable.DraggablePointCanvas;
import com.cueify.gui.draggable.RelocaterFactory;
import com.cueify.output.graph.MutablePointGraph;
import com.cueify.output.graph.PointGraph;
import com.cueify.output.graph.Range;
import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

public abstract class MutablePointGraphDisplay extends CommonGraphDisplay implements DraggableParent, PointGraphDisplay {
	private ArrayList<Canvas> points = new ArrayList<>();

	public MutablePointGraphDisplay(final MutablePointGraph g) {
		super(g);
		g.getPoints().addListener(new InvalidationListener() {
			
			@Override
			public void invalidated(Observable observable) {
				drawLines();
			}
		});
	}
	

	@Override
	public void drawGraph() {
		drawLines();
		drawPoints();
	}
	
	@Override
	public void drawPoints() {
		System.out.println("REDRAWING POINTS");
		removeCurrentPoints();
		PointGraph pg = getMutableGraph();
		for (Point point : pg.getPoints()) {
			addPoint(point);
		}		
	}

	private void removeCurrentPoints() {
		for (Canvas p : points) {
			this.getChildren().remove(p);
		}
		points.clear();
	}

	public void addPoint(Point graphPoint) {
		Point pP = convertGraphPointToDisplayPoint(graphPoint);
		DraggablePointCanvas c = createPointCanvas(pP);
		getChildren().add(c);
		points.add(c);
		linkChangeToPoint(graphPoint, c);
	}

	private DraggablePointCanvas createPointCanvas(Point pixlePoint) {
		double x = pixlePoint.getX();
		double y = pixlePoint.getY();
		Color pointColor = Color.AQUA;
		double pointRadius = 10.0;
		DraggablePointCanvas c = new DraggablePointCanvas(this, new ImmutablePoint(x-pointRadius, y-pointRadius),
				RelocaterFactory.createAnchorRelocator());
		c.setWidth(pointRadius*2);
		c.setHeight(pointRadius*2);
		GraphicsContext gc = c.getGraphicsContext2D();
		gc.setFill(pointColor);
		gc.fillOval(0, 0, pointRadius*2, pointRadius*2);
		return c;
	}

	private void linkChangeToPoint(final Point graphPoint, final DraggablePointCanvas canvas) {
		System.out.println("Change Aplied");
		canvas.point().addListener(new ChangeListener<Point>() {

			@Override
			public void changed(ObservableValue<? extends Point> observable,
					Point oldValue, Point newValue) {
				synchronized(getMutableGraph()) {
					System.out.println("CHANGING POINT");
					getMutableGraph().deletePoint(graphPoint);
					Point transO = translatePointToCenter(oldValue, canvas);
					Point transN = translatePointToCenter(newValue, canvas);
					getMutableGraph().replacePoint(convertPixlPointToGraphPoint(transO), convertPixlPointToGraphPoint(transN));
				}
			}

			private Point translatePointToCenter(Point oldValue, DraggablePointCanvas canvas) {
				double newX = oldValue.getX() + canvas.getWidth()/2;
				double newY = oldValue.getY() + canvas.getHeight()/2;
				return new ImmutablePoint(newX, newY);
			}
		});
	}

	protected Point convertPixlPointToGraphPoint(Point point) {
		double x_cord = Range.convertPointFromRangeToRange(point.getX(),
				new Range(0, this.getWidth()), getXrange());
		double y_cord = Range.convertPointFromRangeToRange(this.getHeight() - point.getY(),
				new Range(0, this.getHeight()), getYrange());
		return new ImmutablePoint(x_cord, y_cord);
	}

	@Override
	public double getMaxX() {
		return getMaxWidth();
	}

	@Override
	public double getMaxY() {
		return getMaxHeight();
	}

	@Override
	public double getMinX() {
		return 0;
	}

	@Override
	public double getMinY() {
		return 0;
	}
	
	//Setters and Getters
	public MutablePointGraph getMutableGraph() {
		return (MutablePointGraph) getGraph();
	}

}
