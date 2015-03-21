package com.cueify.gui.graph.display.helper;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GraphBackground extends Canvas {
	private double boxPixelWidth = 25.0;
	private Color backgroundColor = Color.BLACK;
	private Color boxLineColor = Color.GRAY;
	private double boxLineWidth = 1;
	
	public GraphBackground() {
		draw();
		widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				draw();
			}

		});
		heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable,
					Number oldValue, Number newValue) {
				draw();
			}

		});
	}

	private void draw() {
		drawBackground();
		drawBoxes();
	}

	private void drawBackground() {
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setFill(backgroundColor);
		System.out.println("Height: " + this.getHeight());
		gc.fillRect(0, 0, this.getWidth(), this.getHeight());		
	}
	
	private void drawBoxes() {
		drawHorLines();
		drawVertLines();
	}

	private void drawHorLines() {
		double width = this.getHeight();
		double onLine = boxPixelWidth;
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setStroke(boxLineColor);
		while(onLine <= width) {
			gc.setLineWidth(boxLineWidth);
			gc.strokeLine(0, onLine, this.getWidth(), onLine);
			onLine += boxPixelWidth;
		}
	}

	private void drawVertLines() {
		double width = this.getWidth();
		double onLine = boxPixelWidth;
		GraphicsContext gc = this.getGraphicsContext2D();
		gc.setStroke(boxLineColor);
		while(onLine <= width) {
			gc.setLineWidth(boxLineWidth);
			gc.strokeLine(onLine, 0, onLine, this.getHeight());
			onLine += boxPixelWidth;
		}
	}

	//Getters and Setters
	public double getBoxPixelWidth() {
		return boxPixelWidth;
	}
	public void setBoxPixelWidth(double boxPixelWidth) {
		this.boxPixelWidth = boxPixelWidth;
	}
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	public Color getBoxLineColor() {
		return boxLineColor;
	}
	public void setBoxLineColor(Color boxLineColor) {
		this.boxLineColor = boxLineColor;
	}
	public double getBoxLineWidth() {
		return boxLineWidth;
	}
	public void setBoxLineWidth(double boxLineWidth) {
		this.boxLineWidth = boxLineWidth;
	}

}
