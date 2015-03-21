package com.cueify.gui.controls.validate;

import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public abstract class TextFieldTranslateControlWithFocusRules extends
		TextFieldTranslateControl {

	@Override
	protected void linkValueToTextField() {
		addDefocusUpdate();
		addChangeUpdate();
	}

	private void addChangeUpdate() {
		final ReadOnlyBooleanProperty focusedProperty = focusedProperty();
		getTranslatedProperty().addListener(new ChangeListener<Object>() {

			@Override
			public void changed(ObservableValue<? extends Object> observable,
					Object oldValue, Object newValue) {
				if(focusedProperty.getValue() == false)
					updateTextField();
			}
		});

	}

	private void addDefocusUpdate() {
		this.focusedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable,
					Boolean oldValue, Boolean newValue) {
				if (newValue == false)
					updateTextField();
			}
		});		
	}
	 

	public synchronized void updateTextField() {
		String toSet = getTranslatedProperty().getValue().toString();
		textProperty().setValue(toSet);
	}

}
