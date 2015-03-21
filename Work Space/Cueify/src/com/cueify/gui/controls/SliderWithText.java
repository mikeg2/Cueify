package com.cueify.gui.controls;

import com.cueify.gui.controls.validate.NumberField;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Slider;
import javafx.scene.layout.BorderPane;

public class SliderWithText extends BorderPane{
	private Slider slider = new Slider();
	private NumberField numberField = new NumberField();

	public SliderWithText() {
		numberField.setPrefWidth(50);
		setCenter(slider);
		setRight(numberField);
		numberField.numberProperty.bindBidirectional(slider.valueProperty());
	}
	
	

	public double getMax() {
		return slider.maxProperty().getValue();
	}

	public void setMax(double value) {
		slider.maxProperty().setValue(value);
	}

	public DoubleProperty maxProperty() {
		return slider.maxProperty();
	}


	public double getValue() {
		return slider.valueProperty().getValue();
	}

	public void setValue(double value) {
		slider.valueProperty().setValue(value);
	}

	public DoubleProperty valueProperty() {
		return slider.valueProperty();
	}
	
	

	public double getMin() {
		return slider.minProperty().getValue();
	}

	public void setMin(double value) {
		slider.minProperty().setValue(value);
	}

	public DoubleProperty minProperty() {
		return slider.minProperty();
	}

}
