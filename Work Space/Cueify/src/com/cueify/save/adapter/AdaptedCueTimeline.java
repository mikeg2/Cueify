package com.cueify.save.adapter;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElement;

import com.cueify.time.TimeElapsed;
import com.cueify.timeline.ActiveTimeline;
import com.cueify.timeline.tickable.WaitTimeline;

public class AdaptedCueTimeline {
	private WaitTimeline preWait = new WaitTimeline(new TimeElapsed(0));
	private WaitTimeline postWait = new WaitTimeline(new TimeElapsed(0));
	private ActiveTimeline content = null;
	
	public AdaptedCueTimeline() {
		
	}

	public AdaptedCueTimeline(WaitTimeline preWait2, ActiveTimeline content2,
			WaitTimeline postWait2) {
		setPreWait(preWait2);
		setContent(content2);
		setPostWait(preWait2);
	}

	@XmlElement(name="preWait")
	public WaitTimeline getPreWait() {
		return preWait;
	}
	
	public void setPreWait(WaitTimeline preWait) {
		this.preWait = preWait;
	}
	
	@XmlElement(name="postWait")
	public WaitTimeline getPostWait() {
		return postWait;
	}
	
	public void setPostWait(WaitTimeline preWait) {
		this.postWait = preWait;
	}
	
	@XmlAnyElement(lax=true)
	public ActiveTimeline getContent() {
		return content;
	}
	
	public void setContent(ActiveTimeline content) {
		System.out.println("Set Content: " + content);
		this.content = content;
	}
}
