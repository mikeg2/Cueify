package com.cueify.time;

import javafx.util.Duration;

public class UnknownTime extends TimeElapsed {
	
	public UnknownTime() {
		super(0);
	}

	long timeInMilliseconds = 0;
	
	@Override
	public double getTime(TimeUnit inUnit) {
		return timeInMilliseconds;
	}
	
	@Override
	public double getTime() {
		return timeInMilliseconds;
	}
	
	@Override
	public long getTimeInMilli() {
		return timeInMilliseconds;
	}

	public synchronized TimeElapsed add(TimeElapsed toAdd) {
		return toAdd;
	}

	public synchronized TimeElapsed subtract(TimeElapsed toSub) {
		return new TimeElapsed(timeInMilliseconds).subtract(toSub);
	}

	@Override
	public synchronized int compareTo(TimeElapsed o) {
		long inMilli = (long) o.getTime();
		if(inMilli > this.timeInMilliseconds)
			return -1;
		else if (inMilli == this.timeInMilliseconds)
			return 0;
		else if (inMilli < this.timeInMilliseconds)
			return 1;
		return 0;
	}
	
	@Override
	public synchronized String toString() {
		return String.format("Unknown");
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		return this.getClass().isInstance(obj);
	}

	public Duration toDuration() {
		return Duration.UNKNOWN;
	}
}
