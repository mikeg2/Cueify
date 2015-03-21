package com.cueify.save.adapter;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.cueify.save.adapter.cue.AdaptedTimeElapsed;
import com.cueify.time.TimeElapsed;

public class TimeElapsedAdapter extends XmlAdapter<AdaptedTimeElapsed, TimeElapsed> {

	@Override
	public TimeElapsed unmarshal(AdaptedTimeElapsed v) throws Exception {
		return new TimeElapsed(v.getTime());
	}

	@Override
	public AdaptedTimeElapsed marshal(TimeElapsed v) throws Exception {
		return new AdaptedTimeElapsed(v.getTimeInMilli());
	}

}
