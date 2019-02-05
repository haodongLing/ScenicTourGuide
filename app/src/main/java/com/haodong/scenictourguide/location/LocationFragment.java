package com.haodong.scenictourguide.location;

import android.content.Intent;
import android.util.Log;
import android.widget.TextView;

import com.haodong.scenictourguide.MyRequestCode;
import com.haodong.scenictourguide.MyResultCode;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.app.fragments.MyFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * describe :
 * date on 2019/2/3
 * author linghailong
 * email 105354999@qq.com
 */
public class LocationFragment extends MyFragment {
    @BindView(R.id.tv_location_label)
    TextView mTvLocation;
    @OnClick(R.id.tv_location_label)
    void onLocationClick() {
        startActivityForResult(new Intent(getContext(), CitypickerActivity.class), MyRequestCode
                .REQUEST_CODE_LOCATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MyRequestCode.REQUEST_CODE_LOCATION && resultCode == MyResultCode
                .RESULT_CODE_LOCATION) {
            mTvLocation.setText(TourGuide.getConfiguration(ConfigKeys.CITY));
            String location = data.getStringExtra("location");
            Log.e("tag", "onActivityResult: -------->locationFragment"+location);
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_location;
    }
}
