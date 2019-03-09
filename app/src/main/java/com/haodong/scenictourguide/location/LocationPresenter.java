package com.haodong.scenictourguide.location;

import com.alibaba.fastjson.JSON;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.net.CityInfo;
import com.haodong.scenictourguide.common.net.QueryCityInfoUtils;
import com.haodong.scenictourguide.common.net.RxRestClient;
import com.haodong.scenictourguide.common.net.UrlUtils;
import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.location.data.LocationContact;
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
        WeakHashMap<String, Object> params = new WeakHashMap<>();
       CityInfo.ResultBean cityBean=QueryCityInfoUtils.getInstance().queryCityBean("北京");
        RxRestClient.builder()
                .url(UrlUtils.getUrlByPageAndLocation(cityBean,1))
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .map(new Function<String, ArrayList>() {
                    @Override
                    public ArrayList apply(String s) throws Exception {
                        ScenicBean scenicBean = JSON.parseObject(s, ScenicBean.class);
                        ArrayList<ScenicBean.ResultBean> list = (ArrayList<ScenicBean
                                .ResultBean>) scenicBean.getResult();
                        return list;
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ArrayList>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ArrayList arrayList) {
                        if (isViewAttached()) {
                            if (arrayList.size() == 0) {
                                getView().showError("sss");
                            } else {
                                getView().showData(arrayList);
                                getView().showTab();
                            }
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
