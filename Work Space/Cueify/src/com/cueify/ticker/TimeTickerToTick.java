package com.cueify.ticker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * Provides an easy interface to decide what a TimeTicker ticks.
 * @author Mike
 *
 */
public class TimeTickerToTick implements Iterable<Tickable>{

	private HashSet<Iterable<? extends Tickable>> iterableTickables = new HashSet<Iterable<? extends Tickable>>();
	private HashSet<Tickable> tickables = new HashSet<Tickable>();

	public void addTickable(Tickable tickable) {
		tickables.add(tickable);
	}
	
	public void addIterableTickable(Iterable<? extends Tickable> iTick) {
		iterableTickables.add(iTick);
	}

	@Override
	public Iterator<Tickable> iterator() {
		ArrayList<Tickable> newArray = getArrayOfAllTickables();
		return newArray.iterator();
	}

	private ArrayList<Tickable> getArrayOfAllTickables() {
		ArrayList<Tickable> fromIterable = getTickablesFromIterators();
		fromIterable.addAll(tickables);
		return fromIterable;
	}

	private ArrayList<Tickable> getTickablesFromIterators() {
		ArrayList<Tickable> t = new ArrayList<Tickable>();
		for (Iterable<? extends Tickable> iterable : iterableTickables) {
			for (Tickable tickable : iterable) {
				t.add(tickable);
			}
		}
		return t;
	}
	
	
}
