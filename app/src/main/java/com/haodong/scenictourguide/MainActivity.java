package com.haodong.scenictourguide;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.haodong.scenictourguide.common.app.Activity;

import butterknife.BindView;

import static com.haodong.scenictourguide.R.drawable.selector_tab_main_icon;

public class MainActivity extends Activity {
    @BindView(R.id.navigation)
    AHBottomNavigation bottomNavigation;

    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_main;

    }

    @Override
    protected void initWidget() {
        // Create items
        AHBottomNavigationItem tabMain = new AHBottomNavigationItem(getResources().getString(R
                .string.navigation_main), R.drawable.selector_tab_main_icon);
        AHBottomNavigationItem tabLocation = new AHBottomNavigationItem(getResources().getString(R
                .string.navigation_location), R.drawable.selector_tab_location_icon);
        AHBottomNavigationItem tabHotel = new AHBottomNavigationItem(getResources().getString(R
                .string.navigation_hotel), R.drawable.selector_tab_hotel_icon);
        AHBottomNavigationItem tabTrack = new AHBottomNavigationItem(getResources().getString(R
                .string.navigation_track), R.drawable.selector_tab_track_icon);
        AHBottomNavigationItem tabMine = new AHBottomNavigationItem(getResources().getString(R
                .string.navigation_mine), R.drawable.selector_tab_mine_icon);


// Add items
        bottomNavigation.addItem(tabMain);
        bottomNavigation.addItem(tabLocation);
        bottomNavigation.addItem(tabHotel);
        bottomNavigation.addItem(tabTrack);
        bottomNavigation.addItem(tabMine);
        bottomNavigation.setForceTint(false);
        // 使用圆圈效果
        bottomNavigation.setColored(false);
        bottomNavigation.setSelectedBackgroundVisible(true);
        bottomNavigation.setUseElevation(true);
        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);
    }
}
