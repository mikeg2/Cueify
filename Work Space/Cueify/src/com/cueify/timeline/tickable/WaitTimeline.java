package com.cueify.timeline.tickable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import com.cueify.time.TimeElapsed;

@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement
public class WaitTimeline extends TickableTimeline {
	private ObjectProperty<TimeElapsed> waitTime = new SimpleObjectProperty<>();

	public WaitTimeline(TimeElapsed waitTime) {
		setWaitTime(waitTime);
	}
	
	public WaitTimeline() {
		setWaitTime(new TimeElapsed(0));
	}

	public ObjectProperty<TimeElapsed> absoluteDuration() {
		return waitTime;
	}
	
	@XmlElement
	public TimeElapsed getWaitTime() {
		return waitTime.getValue();
	}

	public void setWaitTime(TimeElapsed waitTime) {
		this.waitTime.setValue(waitTime);
	}
	
	public ObjectProperty<TimeElapsed> waitTime() {
		return waitTime;
	}

}
