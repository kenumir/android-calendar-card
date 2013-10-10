package com.wt.calendarcard;

import java.util.Date;

public class CardGridItem {

	private Integer dayOfMonth;
	private Object data;
	private boolean enabled = true;
	private Date date;
	
	public CardGridItem(Integer dom) { 
		setDayOfMonth(dom);
	}

	public Integer getDayOfMonth() {
		return dayOfMonth;
	}

	public CardGridItem setDayOfMonth(Integer dayOfMonth) {
		this.dayOfMonth = dayOfMonth;
		return this;
	}

	public Object getData() {
		return data;
	}

	public CardGridItem setData(Object data) {
		this.data = data;
		return this;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public CardGridItem setEnabled(boolean enabled) {
		this.enabled = enabled;
		return this;
	}

	public Date getDate() {
		return date;
	}

	public CardGridItem setDate(Date date) {
		this.date = date;
		return this;
	}
	
}
