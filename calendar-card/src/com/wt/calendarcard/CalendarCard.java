package com.wt.calendarcard;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.wt.calendar_card.R;

public class CalendarCard extends RelativeLayout {
	
	private TextView cardTitle;
	private GridView cardGrid;
	private CardGridAdapter mCardGridAdapter;
	private boolean isWidthSet = false;
	private int itemLayout = R.layout.card_item_simple;
	private OnItemRender mOnItemRender;
	private OnCellItemClick mOnCellItemClick;
	private Calendar dateDisplay;

	public CalendarCard(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	
	public CalendarCard(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public CalendarCard(Context context) {
		super(context);
		init(context);
	}
	
	private void init(Context ctx) {
		View layout = LayoutInflater.from(ctx).inflate(R.layout.card_view, null, false);
		
		if (dateDisplay == null)
			dateDisplay = Calendar.getInstance();
		
		cardTitle = (TextView)layout.findViewById(R.id.cardTitle);
		cardGrid = (GridView)layout.findViewById(R.id.cardGrid);
		
		cardTitle.setText(new SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(dateDisplay.getTime()));
		
		mCardGridAdapter = new CardGridAdapter(ctx, getItemLayout(), dateDisplay);
		cardGrid.setAdapter(mCardGridAdapter);
		cardGrid.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
			@Override
			public void onGlobalLayout() {
				final int colSize = (int)((double)cardGrid.getWidth() / 7D);
				if (!isWidthSet) {
					// to jest po to zeby żadnie dopasowało sie do ilosci koumn
					cardGrid.getLayoutParams().width = colSize * 7 + cardGrid.getPaddingLeft(); //(int)(1f * getResources().getDisplayMetrics().density);// + (int)(9F * getResources().getDisplayMetrics().density);//(colSize * 7) - (int)(7F * getResources().getDisplayMetrics().density);
					
					isWidthSet = true;
				}
				mCardGridAdapter.setItemHeight(colSize);
			}
		});
		cardGrid.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				if (getOnCellItemClick() != null)
					getOnCellItemClick().onCellClick(arg1, (CardGridItem)mCardGridAdapter.getItem(arg2));
			}
		});
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		((TextView)layout.findViewById(R.id.cardDay1)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		((TextView)layout.findViewById(R.id.cardDay2)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		((TextView)layout.findViewById(R.id.cardDay3)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		((TextView)layout.findViewById(R.id.cardDay4)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		((TextView)layout.findViewById(R.id.cardDay5)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		((TextView)layout.findViewById(R.id.cardDay6)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
		cal.add(Calendar.DAY_OF_WEEK, 1);
		((TextView)layout.findViewById(R.id.cardDay7)).setText(cal.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, Locale.getDefault()));
		
		addView(layout);
	}

	public int getItemLayout() {
		return itemLayout;
	}

	public void setItemLayout(int itemLayout) {
		this.itemLayout = itemLayout;
		mCardGridAdapter.setItemLayout(itemLayout);
	}

	public OnItemRender getOnItemRender() {
		return mOnItemRender;
	}

	public void setOnItemRender(OnItemRender mOnItemRender) {
		this.mOnItemRender = mOnItemRender;
		mCardGridAdapter.setOnItemRender(mOnItemRender);
	}

	public Calendar getDateDisplay() {
		return dateDisplay;
	}

	public void setDateDisplay(Calendar dateDisplay) {
		this.dateDisplay = dateDisplay;
		cardTitle.setText(new SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(dateDisplay.getTime()));
		mCardGridAdapter.setDate(dateDisplay);
	}

	public OnCellItemClick getOnCellItemClick() {
		return mOnCellItemClick;
	}

	public void setOnCellItemClick(OnCellItemClick mOnCellItemClick) {
		this.mOnCellItemClick = mOnCellItemClick;
	}
	
	/**
	 * call after change any input data - to refresh view
	 */
	public void notifyChanges() {
		mCardGridAdapter.init();
	}

}
