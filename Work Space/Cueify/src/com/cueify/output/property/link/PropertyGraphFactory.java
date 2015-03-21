package com.cueify.output.property.link;

import javafx.beans.property.DoubleProperty;
import javafx.beans.value.WritableValue;

public class PropertyGraphFactory {
	public static PropertyGraphLink<?> createPropertyGraphForProperty(WritableValue<?> property) {
		return PropertyGraphFactory.createPropertyGraphForPropertyType(property.getClass());
	}

	private static PropertyGraphLink<?> createPropertyGraphForPropertyType(Class<?> class1) {
		if (class1 == DoubleProperty.class) {
			return new NumberPropertyGraphLink();
		}
		return null;
	}
}
