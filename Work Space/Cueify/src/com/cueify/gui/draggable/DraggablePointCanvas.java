package com.cueify.gui.draggable;

import com.cueify.output.graph.point.ImmutablePoint;
import com.cueify.output.graph.point.Point;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class DraggablePointCanvas extends Canvas {
	private DoubleProperty xLocation = new SimpleDoubleProperty();
	private DoubleProperty yLocation = new SimpleDoubleProperty();
	private SimpleObjectProperty<Point> publicPoint = new SimpleObjectProperty<Point>(null);
	
	private DraggableParent dragParent;

	public DraggablePointCanvas(final DraggableParent parent, final Point p, final Relocater r) {
		final DraggablePointCanvas sThis = this;
		publicPoint.addListener(new ChangeListener<Point>() {

			@Override
			public void changed(ObservableValue<? extends Point> observable,
					Point oldValue, Point newValue) {
				r.relocate(sThis, newValue);
			}
		});
		publicPoint.set(p);
		this.dragParent = parent;
		this.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public synchronized void handle(MouseEvent arg0) {
				xLocation.set(adjustX(arg0.getX()));
				yLocation.set(adjustY(arg0.getY()));
			}
			
		});
		yLocation.addListener(new ChangeListener<Number>() {

			@Override
			public synchronized void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if(outsideOfTop(newValue.doubleValue())) {
					yLocation.set(0);
					return;
				} else if (outsideOfBottom(newValue.doubleValue())) {
					yLocation.set(dragParent.getMaxY()-sThis.getHeight());
					return;
				}
				publicPoint.set(new ImmutablePoint(publicPoint.get().getX(), newValue.doubleValue()));
			}
		});
		xLocation.addListener(new ChangeListener<Number>() {

			@Override
			public synchronized void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				if(outsideOfLeft(newValue.doubleValue())) {
					xLocation.set(dragParent.getMinX()-sThis.getWidth()/2);
					return;
				} else if (outsideOfRight(newValue.doubleValue())) {
					xLocation.set(dragParent.getMaxX()-sThis.getWidth()/2);
					return;
				}
				publicPoint.set(new ImmutablePoint(newValue.doubleValue(), publicPoint.get().getY()));
			}
		});
	}

	private double adjustY(double sceneY) {
		double startTop = AnchorPane.getTopAnchor(this);
		double shift = this.getHeight()/2.0;
		return startTop + sceneY - shift;
	}

	private double adjustX(double sceneX) {
		double startLeft = AnchorPane.getLeftAnchor(this);
		double shift = this.getWidth()/2.0;
		return startLeft + sceneX - shift;
	}

	// Uses top left corner
	protected boolean outsideOfParent(double x, double y) {
		return outsideOfLeft(x) || outsideOfRight(x) || outsideOfTop(y) || outsideOfBottom(y);
	}

	private boolean outsideOfLeft(double x) {
		return x+this.getWidth() < dragParent.getMinX();
	}

	private boolean outsideOfRight(double x) {
		return x + this.getWidth()/2.0 > dragParent.getMaxX();
	}

	private boolean outsideOfTop(double y) {
		return y < dragParent.getMinY();
	}

	private boolean outsideOfBottom(double y) {
		return y + this.getHeight() > dragParent.getMaxY();
	}
	
	// Getters and Setters
	public Point getPoint() {
		return publicPoint.getValue();
	}

	public void setPoint(Point value) {
		publicPoint.setValue(value);
	}

	public ObjectProperty<Point> point() {
		return publicPoint;
	}
}
