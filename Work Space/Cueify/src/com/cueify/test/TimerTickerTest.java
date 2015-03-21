package com.cueify.test;

import java.util.ArrayList;
import java.util.Iterator;

import com.cueify.ticker.IterableTickable;
import com.cueify.ticker.Tickable;
import com.cueify.ticker.TimeTicker;
import com.cueify.ticker.TimeTickerToTick;
import com.cueify.time.TimeElapsed;

public class TimerTickerTest {

	public static void main(String[] args) {
		performAllTests();
	}

	public static void performAllTests() {
		for(int i = 0; i < 100; i++){
			testSingleTickable();
			testTickableList();
		}
	}

	public static void testTickableList() {
		IterableTickable it = new IterableTickable() {
			private ArrayList<Tickable> list = new ArrayList<>();
			
			{
				list.add(getSingleTickable());
				list.add(getSingleTickable());
			}
			
			@Override
			public Iterator<Tickable> iterator() {
				return list.iterator();
			}
		};
		TimeTicker ticker = new TimeTicker();
		((TimeTickerToTick)ticker.getToTick()).addIterableTickable(it);
		ticker.startTicking();
		
	}

	public static void testSingleTickable() {
		Tickable tick = getSingleTickable();
		TimeTicker ticker = new TimeTicker();
		((TimeTickerToTick)ticker.getToTick()).addTickable(tick);
		ticker.startTicking();
	}
	
	private static Tickable getSingleTickable() {
		return new Tickable() {
			
			@Override
			public void tick(TimeElapsed timeSinceLastTick) {
//				System.out.printf("TICKED:%s \n", String.valueOf(timeSinceLastTick.getTime(TimeUnit.MILLISECOND)));
			}
		};
	}
	
}
