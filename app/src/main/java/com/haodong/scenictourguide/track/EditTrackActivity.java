package com.haodong.scenictourguide.track;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.activities.MyActivity;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.common.utils.AppUtils;
import com.haodong.scenictourguide.location.AttractionFragment;
import com.haodong.scenictourguide.location.AttractionsActivity;

/**
 * describe :
 * date on 2019/4/25
 * author linghailong
 * email 105354999@qq.com
 */
public class EditTrackActivity extends MyActivity {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_edit_track;
    }

    @Override
    protected void initWindows() {
        AppUtils.setInmmersiveStatusBar(EditTrackActivity.this);
    }
    @Override
    protected void initWidget() {
        super.initWidget();
    }

    @Override
    protected void initData() {
        super.initData();
        MyFragment fragment = findFragment(DynamicFragment.class);
        DynamicFragment dynamicFragment=new DynamicFragment();
        Bundle bundle=new Bundle();
        bundle.putString("intentFrom","track");
        dynamicFragment.setArguments(bundle);
        if (fragment == null) {
            loadRootFragment(R.id.edit_tract_root_view, dynamicFragment);
        }
    }

}
