package com.haodong.scenictourguide.track;

import android.net.Uri;

import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.main.MainData;
import com.haodong.scenictourguide.track.contractor.DynamicContract;
import com.haodong.scenictourguide.track.recycler.SlideImage;

import java.util.ArrayList;
import java.util.List;

/**
 * describe :
 * date on 2019/4/13
 * author linghailong
 * email 105354999@qq.com
 */
public class DynamicPresenter extends BasePresenter<DynamicFragment> implements DynamicContract.Presenter {
    DynamicContract.view mView;
    private UploadRunnable uploadRunnable;

    public DynamicPresenter(DynamicFragment view) {
        super(view);
        this.mView = getView();
    }

    @Override
    public ArrayList<SlideImage> getSlideArray(List<Uri> uriList, List<String> pathList) {
        ArrayList<SlideImage> arrayList = new ArrayList<>();
        int size = uriList.size();
        for (int i = 0; i < size; i++) {
            SlideImage slideImage = new SlideImage();
            slideImage.setPath(pathList.get(i));
            slideImage.setUri(uriList.get(i));
            arrayList.add(slideImage);
        }
        return arrayList;
    }

    @Override
    public void upLoad(MainData.MainDataInfo mainDataInfo) {
        uploadRunnable = new UploadRunnable(mainDataInfo, new UploadRunnable.OnUploadFinishListener() {
            @Override
            public void onFinish() {
                getView().hideProgress();
            }
        });
        uploadRunnable.run();
    }

}
