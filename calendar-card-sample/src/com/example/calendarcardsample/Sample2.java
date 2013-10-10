package com.example.calendarcardsample;

import android.app.Activity;
import android.os.Bundle;

import com.wt.calendarcard.CalendarCardPager;

public class Sample2 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		CalendarCardPager mCalendarCardPager = new CalendarCardPager(this);
		
		setContentView(mCalendarCardPager);
	}

}
