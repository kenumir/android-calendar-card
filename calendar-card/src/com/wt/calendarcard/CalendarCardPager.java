package com.wt.calendarcard;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

public class CalendarCardPager extends ViewPager {
	
	private CardPagerAdapter mCardPagerAdapter;

	public CalendarCardPager(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public CalendarCardPager(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context context) {
		 mCardPagerAdapter = new CardPagerAdapter(context);
		 setAdapter(mCardPagerAdapter);
	}
	
	public CardPagerAdapter getCardPagerAdapter() {
		return mCardPagerAdapter;
	}

}
