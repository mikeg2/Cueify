package com.cueify.output;

import javafx.beans.property.SimpleObjectProperty;
import com.cueify.port.Port;
import com.cueify.timeline.ReadyTimeline;

public interface Output extends ReadyTimeline {
	public void setPort(Port port);
	public Port getPort();
	public SimpleObjectProperty<Port> port();
}
