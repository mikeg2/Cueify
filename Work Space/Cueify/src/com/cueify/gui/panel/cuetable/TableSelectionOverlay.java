package com.cueify.gui.panel.cuetable;

import javafx.application.Platform;
import javafx.scene.canvas.Canvas;
import javafx.scene.paint.Color;

public class TableSelectionOverlay extends Canvas {
	public TableSelectionOverlay(CueTable n) {
//		l.invalidated(null);
//		widthProperty().addListener(l);
//		heightProperty().addListener(l);
		widthProperty().bind(n.widthProperty());
		heightProperty().bind(n.heightProperty());
		this.setOpacity(0.0);
		this.setPickOnBounds(true);
	}

	private void drawCanvas() {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				System.out.println("Redraw");
				getGraphicsContext2D().setFill(Color.BLUE);
				getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());
			}
		});
	}
	
	public void show() {
		drawCanvas();
		this.setOpacity(0.75);
	}
	
	public void hide() {
		this.setOpacity(0.0);
	}
}
