package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.haodong.scenictourguide.MainActivity;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.activities.MyActivity;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.common.utils.AppUtils;
import com.haodong.scenictourguide.location.data.ScenicBean;
import com.haodong.scenictourguide.track.DynamicFragment;

import static com.haodong.scenictourguide.location.IntentKeys.INTENT_KEY_DATA;

public class AttractionsActivity extends MyActivity {


    @Override
    protected int getContentLayoutId() {
        return R.layout.activity_attractions;
    }

    @Override
    protected void initWindows() {
        AppUtils.setInmmersiveStatusBar(AttractionsActivity.this);
    }
    @Override
    protected void initWidget() {
        super.initWidget();
    }

    @Override
    protected void initData() {
        super.initData();
        MyFragment fragment = findFragment(AttractionFragment.class);
        AttractionFragment attractionFragment = AttractionFragment.newInstance();
        if (fragment == null) {
            loadRootFragment(R.id.attractions_root_view, attractionFragment);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode== DynamicFragment.REQUEST_CODE_CHOOSE&&resultCode==RESULT_OK){
            findFragment(DynamicFragment.class).onActivityResult(requestCode,resultCode,data);
        }
    }
}
