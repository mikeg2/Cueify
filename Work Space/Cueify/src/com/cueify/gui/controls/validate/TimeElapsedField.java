package com.cueify.gui.controls.validate;

import com.cueify.time.TimeElapsed;
import com.cueify.time.TimeUnit;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;

public class TimeElapsedField extends TextFieldTranslateControlWithFocusRules {
	public static final String FULL_TEXT_REGEX = "([.]\\d+|\\d+[.]?\\d*):([.]\\d+|\\d+[.]?\\d*):([.]\\d+|\\d+[.]?\\d*).([.]\\d+|\\d+[.]?\\d*)";
	public static final String FULL_TEXT_WO_DEC_REGEX = "\\d{2}:\\d{2}:\\d{2}";
	public static final String SECOND_TEXT_REGEX = "([.]\\d+|\\d+([.]\\d+)?)";
	public static final String SECOND_DEC_TEXT_REGEX = "[1-60].[1-100]";
	
	private ObjectProperty<TimeElapsed> timeElapsedProperty;

	public TimeElapsedField() {
		super();
	}

	protected void setDefaultValue() {
		setTimeElapsed(new TimeElapsed(0));
	}

	public TimeElapsed getTimeElapsed() {
		return timeElapsedProperty.getValue();
	}

	public void setTimeElapsed(TimeElapsed toSet) {
		timeElapsedProperty.setValue(toSet);
		updateTextField();
	}
	
	public ObjectProperty<TimeElapsed> timeElapsed() {
		return timeElapsedProperty;
	}

	@Override
	protected void saveStringAsValue(String toConvert) {
		TimeElapsed converted = convertStringToTimeElapsed(toConvert);
		timeElapsedProperty.setValue(converted);
	}

	protected static TimeElapsed convertStringToTimeElapsed(String toConvert) {
		try {
			if (toConvert.matches(FULL_TEXT_REGEX) /* || toConvert.matches(FULL_TEXT_WO_DEC_REGEX)*/)  {
				return convertFullTextToTimeElapsed(toConvert);
			} else if (toConvert.matches(SECOND_DEC_TEXT_REGEX)) {
				return convertMinuteDecToTimeElapsed(toConvert);
			} else if (toConvert.matches(SECOND_TEXT_REGEX)) {
				return convertMinuteToTimeElapsed(toConvert);
			}
		} catch(Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
		throw new IllegalArgumentException();
	}

	private static TimeElapsed convertMinuteToTimeElapsed(String toConvert) {
		return new TimeElapsed(TimeUnit.SECOND, Long.valueOf(toConvert));
	}

	private static TimeElapsed convertMinuteDecToTimeElapsed(String toConvert) {
		double seconds = Double.valueOf(toConvert);
		return new TimeElapsed(TimeUnit.SECOND, seconds);
	}

	private static TimeElapsed convertFullTextToTimeElapsed(String toConvert) {
		String split[] = toConvert.split(":");
		System.out.print(split.toString());
		double totalSeconds = 0;
		totalSeconds += Double.valueOf(split[2]);
		totalSeconds += Integer.valueOf(split[1]) * 60;
		totalSeconds += Integer.valueOf(split[0]) * 60 * 60;
		return new TimeElapsed(TimeUnit.SECOND, totalSeconds);
	}

	@Override
	protected void initNewProperty() {
		timeElapsedProperty = new SimpleObjectProperty<TimeElapsed>();		
	}

	@Override
	public ObservableValue<? extends Object> getTranslatedProperty() {
		return timeElapsedProperty;
	}
}
