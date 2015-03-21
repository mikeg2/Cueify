package com.cueify.gui.graph.overlay;

import com.cueify.gui.graph.display.GraphDisplay;
import com.cueify.output.graph.point.Point;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class GraphicScrubOverlay extends AnchorPane {
	GraphDisplay under;
	Canvas over = new Canvas();
	
	Color lineColor = Color.YELLOW;
	double lineWidth = 2.0;
	Color pointColor = Color.YELLOW;
	double pointRadius = 6.0;
	
	public GraphicScrubOverlay(final GraphDisplay under) {
		this.under = under;
		this.prefWidthProperty().bind(under.widthProperty());
		this.prefHeightProperty().bind(under.heightProperty());
		over.widthProperty().bind(this.widthProperty());
		over.heightProperty().bind(this.heightProperty());
		over.setOnMouseMoved(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				over.getGraphicsContext2D().clearRect(0, 0,	over.getWidth(), over.getHeight());
				drawLineAt(event.getX());
			}
		});
		this.getChildren().add((Node) under);
		this.getChildren().add(over);
		setPickOnBounds(false);
	}
	
	private void drawLineAt(double x) {
		Point[] points = under.getDisplayPointsAt(x);
//		drawVertLine(x);
	}

	private void drawPoints(Point[] points) {
		for (Point point : points) {
			drawPoint(point);
		}
	}

	private void drawPoint(Point point) {
		final GraphicsContext gc = over.getGraphicsContext2D();
		gc.setFill(pointColor);
		gc.fillOval(point.getX()-pointRadius, point.getY()-pointRadius, pointRadius*2, pointRadius*2);
	}

	private void drawVertLine(double x) {
		final GraphicsContext gc = over.getGraphicsContext2D();
		gc.setStroke(lineColor);
		gc.setLineWidth(lineWidth);
		gc.strokeLine(x, this.getHeight(), x, 0);
	}
}
