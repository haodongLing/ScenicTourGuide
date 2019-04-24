package com.haodong.scenictourguide.track.contractor;

import android.net.Uri;

import com.haodong.scenictourguide.common.presenter.BaseContract;
import com.haodong.scenictourguide.track.recycler.SlideImage;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :动态
 * date on 2019/4/13
 * author linghailong
 * email 105354999@qq.com
 */
public interface DynamicContract {
    interface Presenter extends BaseContract.Presenter{
        ArrayList<SlideImage> getSlideArray(List<Uri> uriList, List<String> pathList);
    }
    interface  view extends BaseContract.View<DynamicContract.Presenter>{

    }
}
