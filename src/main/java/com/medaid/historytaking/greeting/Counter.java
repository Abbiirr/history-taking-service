package com.medaid.historytaking.greeting;

import com.medaid.historytaking.logging.annotations.LoggingClass;
import com.medaid.historytaking.logging.annotations.NoLogging;
import org.springframework.stereotype.Component;

@Component
@LoggingClass
public class Counter {
	
	private int count;
	
	public int count() {
		return count;
	}
	
	@NoLogging
	public void increment(int i) {
		count += i;
	}
	
}
