package com.cueify.output.property;

import javafx.beans.value.WritableValue;

import com.cueify.output.graph.FlatGraph;
import com.cueify.output.property.link.NumberPropertyGraphLink;
import com.cueify.timeline.PassiveTimeline;


public class AudioPropertyTimelineUnifyer extends PropertyTimelineUnifyer{
	private NumberPropertyGraphLink volume = new NumberPropertyGraphLink();
	private NumberPropertyGraphLink rate = new NumberPropertyGraphLink();
	
	public AudioPropertyTimelineUnifyer(PassiveTimeline t) {
		super(t);
	}

	@Override
	protected void instantiateDefaultPropertyTimelines() {
		volume.setGraph(new FlatGraph(1.0));
		rate.setGraph(new FlatGraph(1.0));
	}

	public void linkVolumeProperty(WritableValue<Number> volume) {
		this.volume.setProperty(volume);
	}
	
	public void linkRateProperty(WritableValue<Number> rate) {
		this.rate.setProperty(rate);
	}

	// Setters and Getters
	public NumberPropertyGraphLink getVolumeLink() {
		return volume;
	}

	public NumberPropertyGraphLink getRateLink() {
		return rate;
	}
}
