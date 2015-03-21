package com.cueify.gui.controls.validate;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.value.ObservableValue;

public class NumberField extends TextFieldTranslateControlWithFocusRules {
	
	public DoubleProperty numberProperty;
	
	@Override
	protected void initNewProperty() {
		this.numberProperty = new SimpleDoubleProperty();
	}

	@Override
	protected void saveStringAsValue(String toConvert) {
		try {
			double value = Double.valueOf(toConvert);
			this.numberProperty.setValue(value);
		} catch (Exception e) {
			throw new IllegalArgumentException();
		}
	}

	@Override
	protected void setDefaultValue() {
		numberProperty.setValue(0);
	}

	@Override
	public void updateTextField() {
		textProperty().setValue(Double.toString(numberProperty.getValue()));
	}

	public double getNumber() {
		return numberProperty.getValue();
	}
	
	public void setNumber(double number) {
		numberProperty.setValue(number);
	}
	
	public DoubleProperty number() {
		return numberProperty;
	}

	@Override
	public ObservableValue<? extends Object> getTranslatedProperty() {
		return number();
	}

}
