package com.example.spinnertestapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.ListPopupWindow;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class CustomSpinnerWithScrollbar extends AppCompatSpinner {
    public CustomSpinnerWithScrollbar(Context context) {
        super(context);
    }
    @Override
    public boolean performClick()
    {
        boolean bClicked = super.performClick();
        try
        {
            Field mPopupField = Spinner.class.getDeclaredField("mPopup");
            mPopupField.setAccessible(true);
            ListPopupWindow pop = (ListPopupWindow) mPopupField.get(this);
            ListView listview = pop.getListView();
            listview.setScrollbarFadingEnabled(false);
            Field mScrollCacheField = View.class.getDeclaredField("mScrollCache");
            mScrollCacheField.setAccessible(true);
            Object mScrollCache = mScrollCacheField.get(listview);
            Field scrollBarField = mScrollCache.getClass().getDeclaredField("scrollBar");
            scrollBarField.setAccessible(true);
            Object scrollBar = scrollBarField.get(mScrollCache);
            Method method_thumb = scrollBar.getClass().getDeclaredMethod("setVerticalThumbDrawable", Drawable.class);
            method_thumb.setAccessible(true);
            method_thumb.invoke(scrollBar, getResources().getDrawable(R.drawable.scrollbar_rounded_corner));
            Method method_track = scrollBar.getClass().getDeclaredMethod("setVerticalTrackDrawable", Drawable.class);
            method_track.setAccessible(true);
            method_track.invoke(scrollBar, getResources().getDrawable(R.drawable.scrollbar_track_spinner));

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            {
                Field mVerticalScrollbarPositionField = View.class.getDeclaredField("mVerticalScrollbarPosition");
                mVerticalScrollbarPositionField.setAccessible(true);
                mVerticalScrollbarPositionField.set(listview, SCROLLBAR_POSITION_RIGHT);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        return bClicked;
    }
}
