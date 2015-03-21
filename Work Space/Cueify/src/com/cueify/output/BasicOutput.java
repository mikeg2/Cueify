package com.cueify.output;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import com.cueify.port.Port;
import com.cueify.timeline.TimelineState;
import com.cueify.timeline.tickable.AutoStopTimeline;

public abstract class BasicOutput extends AutoStopTimeline implements FileBasedOutput{
	private SimpleObjectProperty<Port> displayPortProperty = new SimpleObjectProperty<Port>();
	private ObjectProperty<TimelineState> state = new SimpleObjectProperty<TimelineState>(TimelineState.FINISHED);

	public void setPort(Port port) {
		this.displayPortProperty.set(port);
	}
	
	public Port getPort() {
		return displayPortProperty.get();
	}
	
	public SimpleObjectProperty<Port> port() {
		return displayPortProperty;
	}

	@Override
	public TimelineState getState() {
		return state.getValue();
	}

	@Override
	public void setState(TimelineState state) {
		this.state.set(state);
	}

	@Override
	public ObjectProperty<TimelineState> state() {
		return this.state;
	}
	
	@Override
	public void play() {
		setState(TimelineState.PLAY);
	}

	@Override
	public void pause() {
		setState(TimelineState.PAUSE);
	}

	@Override
	public void stop() {
		setState(TimelineState.STOPPED);
	}
	
	@Override
	protected void finish() {
		state().set(TimelineState.FINISHED);
	}
}
