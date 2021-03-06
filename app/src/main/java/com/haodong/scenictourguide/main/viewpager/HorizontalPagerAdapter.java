package com.haodong.scenictourguide.main.viewpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.haodong.scenictourguide.R;

import static com.haodong.scenictourguide.main.viewpager.ViewPagerUtils.setupItem;

public class HorizontalPagerAdapter extends PagerAdapter {

    private final ViewPagerUtils.LibraryObject[] LIBRARIES = new ViewPagerUtils.LibraryObject[]{
            new ViewPagerUtils.LibraryObject(
                    R.drawable.img_jingzhou1
            ),
            new ViewPagerUtils.LibraryObject(
                    R.drawable.img_jingzhou2
            ),
            new ViewPagerUtils.LibraryObject(
                    R.drawable.img_jingzhou4
            )
    };

    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public HorizontalPagerAdapter(final Context context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return LIBRARIES.length;
    }

    @Override
    public int getItemPosition(final Object object) {
        return POSITION_NONE;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, final int position) {
        final View view;
        view = mLayoutInflater.inflate(R.layout.item, container, false);
        setupItem(view, LIBRARIES[position]);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(final View view, final Object object) {
        return view.equals(object);
    }

    @Override
    public void destroyItem(final ViewGroup container, final int position, final Object object) {
        container.removeView((View) object);
    }
}
