package com.cueify.save.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.cueify.time.TimeElapsed;
import com.cueify.timeline.tickable.cue.CueTimeline;

public class CueTimelineAdapter extends XmlAdapter<AdaptedCueTimeline, CueTimeline> {

	@Override
	public CueTimeline unmarshal(AdaptedCueTimeline v) throws Exception {
		CueTimeline c =  new CueTimeline(v.getContent());
		c.getPostWait().setWaitTime(v.getPostWait().getWaitTime());
		c.getPreWait().setWaitTime(v.getPreWait().getWaitTime());
		return c;
	}

	@Override
	public AdaptedCueTimeline marshal(CueTimeline v) throws Exception {
		return new AdaptedCueTimeline(v.getPreWait(), v.getContent(), v.getPostWait());
	}

}
