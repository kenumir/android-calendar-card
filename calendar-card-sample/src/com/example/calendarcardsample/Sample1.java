package com.example.calendarcardsample;

import android.app.Activity;
import android.os.Bundle;

import com.wt.calendarcard.CalendarCard;

public class Sample1 extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		CalendarCard mCalendarCard = new CalendarCard(this);
		
		setContentView(mCalendarCard);
	}

}
