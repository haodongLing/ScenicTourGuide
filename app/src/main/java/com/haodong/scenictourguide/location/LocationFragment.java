package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.haodong.scenictourguide.MyRequestCode;
import com.haodong.scenictourguide.MyResultCode;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;

import butterknife.BindView;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationFragment extends PresenterFragment<LocationPresenter> {
    @BindView(R.id.rv_location)
    RecyclerView mRecyclerView;
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyRequestCode.REQUEST_CODE_LOCATION && resultCode == MyResultCode
                .RESULT_CODE_LOCATION) {
            String location = data.getStringExtra("location");
            Log.e("tag", "onActivityResult: -------->locationFragment" + location);
        }
    }


    @Override
    public LocationPresenter initPresenter() {
        return new LocationPresenter(this);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        mRecyclerView.setAdapter();

    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_location;
    }
}
