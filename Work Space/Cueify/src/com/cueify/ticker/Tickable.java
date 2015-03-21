package com.cueify.ticker;

import com.cueify.time.TimeElapsed;

public interface Tickable {
	public void tick(TimeElapsed timeSinceLastTick);
}
