package com.cueify.time;

public enum TimeUnit {
	HOUR(3600000),
	MINUTE(60000),
	SECOND(1000),
	MILLISECOND(1);
	
  private int valueInMillisecond;    

  private TimeUnit(int value) {
    this.valueInMillisecond = value;
  }

  public int getValueInMillisecond() {
    return valueInMillisecond;
  }
  
	public static double convertUnits(TimeUnit fromUnit, TimeUnit toUnit, double value) {
		long valueInMilliseconds = (long) (fromUnit.getValueInMillisecond() * value);
		double valueInToUnit = valueInMilliseconds / (double) toUnit.getValueInMillisecond();
		return valueInToUnit;
	}

	public static double convertUnits(TimeUnit fromUnit, TimeUnit toUnit, long value) {
		double valueInMilliseconds = (long) (fromUnit.getValueInMillisecond() * value);
		double valueInToUnit = valueInMilliseconds / toUnit.getValueInMillisecond();
		return valueInToUnit;
	}
}
