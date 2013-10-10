package com.wt.calendarcard;

import java.util.ArrayList;
import java.util.Calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

public class CardGridAdapter extends BaseAdapter {

	private Context mContext;
	private LayoutInflater mLayoutInflater;
	private ArrayList<CardGridItem> items = new ArrayList<CardGridItem>();
	private int mItemHeight = 0;
	private GridView.LayoutParams mImageViewLayoutParams;
	private int itemLayout;
	private OnItemRender mOnItemRender;
	private Calendar date;
	
	private int getDaySpacing(int dayOfWeek) {
		if (Calendar.SUNDAY == dayOfWeek)
			return 6;
		else
			return dayOfWeek - 2;
		/*switch(dayOfWeek) {
			default: return 0;
			case 1: return 6; // nd
			case 2: return 7; // pn
			case 3: return 1+7; // wt
			case 4: return 2; // sr
			case 5: return 3; // cz
			case 6: return 4; // pt
			case 7: return 5; // sob
		}*/
		// 1 = 6
		// 2 = 7
		// 3 = 1
		// 4 = 2
		// 5 = 3
		// 6 = 4
		// 7 = 5
	}
	
	private int getDaySpacingEnd(int dayOfWeek) {
		/*if (Calendar.SUNDAY == dayOfWeek)
			return 7;
		else
			return 7 - (dayOfWeek - 1);*/
		return 8 - dayOfWeek;
		/*switch(dayOfWeek) {
			default: return 0;
			case 1: return 7; // nd
			case 2: return 6; // pn
			case 3: return 5; // wt
			case 4: return 4; // sr
			case 5: return 3; // cz
			case 6: return 2; // pt
			case 7: return 1; // sob
		}*/
	}
	
	public void init() {
		items.clear();
		Calendar cal;
		if (date != null) 
			cal = (Calendar)date.clone();
		else
			cal = Calendar.getInstance();
		
		cal.set(Calendar.DAY_OF_MONTH, 1);
		
		int daySpacing = getDaySpacing(cal.get(Calendar.DAY_OF_WEEK));
		
		if (daySpacing > 0) {
			Calendar prevMonth = Calendar.getInstance();
			prevMonth.add(Calendar.MONTH, -1);
			prevMonth.set(Calendar.DAY_OF_MONTH, prevMonth.getActualMaximum(Calendar.DAY_OF_MONTH));
			int lastDayOfPrevMonth = prevMonth.get(Calendar.DAY_OF_MONTH)+1;
			for(int i=lastDayOfPrevMonth-daySpacing+1; i<lastDayOfPrevMonth+1; i++) {
				items.add(new CardGridItem(i).setEnabled(false));
			}
		}
		
		int firstDay = cal.get(Calendar.DAY_OF_MONTH);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		int lastDay = cal.get(Calendar.DAY_OF_MONTH)+1;
		for(int i=firstDay; i<lastDay; i++) {
			cal.set(Calendar.DAY_OF_MONTH, i-1);
			items.add(new CardGridItem(i).setDate(cal.getTime()));
		}
		
		if (date != null) 
			cal = (Calendar)date.clone();
		else
			cal = Calendar.getInstance();
		
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		
		daySpacing = getDaySpacingEnd(cal.get(Calendar.DAY_OF_WEEK));

		if (daySpacing > 0) {
			for(int i=0; i<daySpacing; i++)
				items.add(new CardGridItem(i+1).setEnabled(false));
		}

		notifyDataSetChanged();

	}
	
	public CardGridAdapter(Context ctx, int itemLay, Calendar d) {
		mContext = ctx;
		mLayoutInflater = LayoutInflater.from(mContext);
		mImageViewLayoutParams = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, GridView.LayoutParams.MATCH_PARENT);
		itemLayout = itemLay;
		date = d;
		init();
	}

	@Override
	public int getCount() {
		return items.size();
	}

	@Override
	public Object getItem(int pos) {
		return items.get(pos);
	}

	@Override
	public long getItemId(int pos) {
		return pos;
	}

	@Override
	public View getView(int pos, View v, ViewGroup root) {
		v = mLayoutInflater.inflate(itemLayout, root, false);
		CardGridItem item = items.get(pos);
		v.setLayoutParams(mImageViewLayoutParams);
		
		if (getOnItemRender() != null) {
			
			getOnItemRender().onRender(v, item);
		} else {
			((TextView)v).setText(item.getDayOfMonth().toString());
			v.setEnabled(item.isEnabled());
		}
		return v;
	}
	
	public void setItemHeight(int height) {
		if (height == mItemHeight)
			return;
		mItemHeight = height;
		mImageViewLayoutParams = new GridView.LayoutParams(GridView.LayoutParams.MATCH_PARENT, mItemHeight);
		notifyDataSetChanged();
	}

	public OnItemRender getOnItemRender() {
		return mOnItemRender;
	}

	public void setOnItemRender(OnItemRender mOnItemRender) {
		this.mOnItemRender = mOnItemRender;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}
	
	public void setItemLayout(int r) {
		itemLayout= r;
	}
	
	public ArrayList<CardGridItem> getItems() {
		return items;
	}
	
}
