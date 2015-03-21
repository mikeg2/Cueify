package com.cueify.timeline.tickable.cue;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import com.cueify.output.AudioOutput;
import com.cueify.time.TimeElapsed;
import com.cueify.timeline.ActiveTimeline;
import com.cueify.timeline.TimelineState;
import com.cueify.timeline.tickable.SuperTimeline;
import com.cueify.timeline.tickable.WaitTimeline;

@XmlSeeAlso({WaitTimeline.class, AudioOutput.class})
@XmlAccessorType(XmlAccessType.NONE)
@XmlJavaTypeAdapter(com.cueify.save.adapter.CueTimelineAdapter.class)
public class CueTimeline extends SuperTimeline {
	private final WaitTimeline preWait = new WaitTimeline(new TimeElapsed(0));
	private final WaitTimeline postWait = new WaitTimeline(new TimeElapsed(0));
	private final ActiveTimeline content;
	
	public CueTimeline(final ActiveTimeline content) {
		this.content = content;
		content.state().addListener(new ChangeListener<TimelineState>() {

			@Override
			public void changed(
					ObservableValue<? extends TimelineState> observable,
					TimelineState oldValue, TimelineState newValue) {
				System.out.println(oldValue + " -> " + newValue);
			}
		});
		this.addTimeline(preWait);
		this.addTimeline(content);
		this.addTimeline(postWait);
	}

	@XmlElement(name="preWait")
	public WaitTimeline getPreWait() {
		return preWait;
	}
	
	@XmlElement(name="postWait")
	public WaitTimeline getPostWait() {
		return postWait;
	}
	
	@XmlAnyElement
	public ActiveTimeline getContent() {
		return content;
	}
}
