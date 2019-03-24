package com.haodong.scenictourguide.location;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.haodong.scenictourguide.common.net.RxRestClient;
import com.haodong.scenictourguide.common.net.api.NormalRequest;
import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.location.data.LocationContact;
import com.haodong.scenictourguide.location.data.LocationDataConverter;
import com.haodong.scenictourguide.location.data.ScenicBean;

import java.util.ArrayList;
import java.util.WeakHashMap;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class LocationPresenter extends BasePresenter<LocationFragment> implements
        LocationContact.Presenter {

    LocationPresenter(LocationFragment view) {
        super(view);
    }

    @Override
    public void loadFirstPage() {
        String url = new NormalRequest("http://route.showapi.com/268-1")
                .addTextPara("keyword", "北京")
                .addTextPara("page", "1")
                .getUrlString();

        WeakHashMap<String, Object> params = new WeakHashMap<>();
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .map(new Function<String, ScenicBean>() {
                    @Override
                    public ScenicBean apply(String s) throws Exception {
                        ScenicBean scenicBean = (ScenicBean) new LocationDataConverter().setJsonData(s).convert();
                        Log.e("lhl", "apply: "+scenicBean.getAllPages() );
                        return scenicBean;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScenicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicBean scenicBean) {
                        if (isViewAttached()) {
                            Log.e("lhl", "onNext: "+scenicBean.getAllPages() );
//                            if (arrayList.size() == 0) {
//                                getView().showError("sss");
//                            } else {
//                                getView().showData(arrayList);
//                                getView().showTab();
//                            }
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (isViewAttached()) {
                        }

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void loadMorePage(int i) {

    }

    @Override
    public void onRefresh() {

    }
}
