package com.cueify.time;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.util.Duration;

@XmlJavaTypeAdapter(com.cueify.save.adapter.TimeElapsedAdapter.class)
public class TimeElapsed implements Comparable<TimeElapsed> {
	private final long timeInMilliseconds;
	public static final TimeElapsed UKNOWN = new UnknownTime();

	public TimeElapsed(TimeUnit unit, double value) {
		timeInMilliseconds = (long) TimeUnit.convertUnits(unit, TimeUnit.MILLISECOND, value);
	}

	public TimeElapsed(TimeUnit unit, long value) {
		timeInMilliseconds = (long) TimeUnit.convertUnits(unit, TimeUnit.MILLISECOND, value);
	}

	public TimeElapsed(long value) {
		this(TimeUnit.MILLISECOND, value);
	}
	
	public TimeElapsed(Duration duration) {
		timeInMilliseconds = (long) duration.toMillis();
	}
	
	public double getTime(TimeUnit inUnit) {
		return TimeUnit.convertUnits(TimeUnit.MILLISECOND, inUnit, this.timeInMilliseconds);
	}
	
	public double getTime() {
		return this.getTime(TimeUnit.MILLISECOND);
	}
	
	public long getTimeInMilli() {
		return timeInMilliseconds;
	}

	public synchronized TimeElapsed add(TimeElapsed toAdd) {
		return new TimeElapsed(this.timeInMilliseconds + toAdd.getTimeInMilli());
	}

	public synchronized TimeElapsed subtract(TimeElapsed toSub) {
		return new TimeElapsed(this.timeInMilliseconds - toSub.getTimeInMilli());
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
		long hrs = (long) getTime(TimeUnit.HOUR);
		long min = (long) getTime(TimeUnit.MINUTE) % 60;
		double sec = getTime(TimeUnit.SECOND) % 60.0;
		return String.format("%02d:%02d:%02.2f", hrs, min, sec);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		TimeElapsed obj_t = (TimeElapsed) obj;
		return obj_t.getTimeInMilli() == this.getTimeInMilli();
	}

	public Duration toDuration() {
		return new Duration(this.timeInMilliseconds);
	}
}
