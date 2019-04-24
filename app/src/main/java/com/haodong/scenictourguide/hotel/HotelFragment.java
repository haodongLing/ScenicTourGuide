package com.haodong.scenictourguide.hotel;

import android.view.View;

import com.gigamole.infinitecycleviewpager.HorizontalInfiniteCycleViewPager;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.hotel.contract.HotelContract;
import com.haodong.scenictourguide.main.viewpager.HorizontalPagerAdapter;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class HotelFragment extends PresenterFragment<HotelContract.Presenter>implements HotelContract.View{
    private HorizontalInfiniteCycleViewPager mViewPager;
    @Override
    public Object setLayout() {
        return R.layout.fragment_hotel;
    }

    @Override
    public HotelPresenter initPresenter() {
        return new HotelPresenter(this);
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mViewPager=root.findViewById(R.id.hotel_viewpager);
        mViewPager.setAdapter(new HotelPagerAdapter(getContext()));
    }
}
