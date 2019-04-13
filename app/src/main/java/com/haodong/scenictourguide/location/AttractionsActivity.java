package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.haodong.scenictourguide.MainActivity;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.activities.MyActivity;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;
import com.haodong.scenictourguide.common.utils.AppUtils;
import com.haodong.scenictourguide.location.data.ScenicBean;

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
        if (getIntent().getParcelableExtra(INTENT_KEY_DATA) != null) {
            ScenicBean.ContentlistBean contentlistBean=
                    getIntent().getParcelableExtra(INTENT_KEY_DATA);
            Log.e("lhl",
                    "initArgs: "+contentlistBean.getPicList().toString()+contentlistBean.getAttention().toString() );
            Bundle bundle = new Bundle();
            bundle.putParcelable(INTENT_KEY_DATA, getIntent().getParcelableExtra(INTENT_KEY_DATA));
            bundle.putString("1","s");
            attractionFragment.setArguments(bundle);
        }
        if (fragment == null) {
            loadRootFragment(R.id.attractions_root_view, attractionFragment);
        }
    }
}
