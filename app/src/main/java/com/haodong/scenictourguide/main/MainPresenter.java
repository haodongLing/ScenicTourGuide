package com.haodong.scenictourguide.main;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.haodong.scenictourguide.common.net.RxRestClient;
import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.hotel.data.HotelBean;
import com.haodong.scenictourguide.hotel.data.HotelDataConverter;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * describe :
 * date on 2019/4/25
 * author linghailong
 * email 105354999@qq.com
 */
public class MainPresenter extends BasePresenter<MainContract.View>implements MainContract.Presenter {
    private MainContract.View mView=null;
    public MainPresenter(MainFragment view) {
        super(view);
        this.mView=view;
    }
    @Override
    public void loadData() {
        RxRestClient.builder()
                .url("http://47.93.39.51/scenic")
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .map(new Function<String,MainData>() {
                    @Override
                    public MainData apply(String s) throws Exception {
                        return (MainData) new MainDataConverter().setJsonData(s).convert();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MainData>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(MainData mainData) {
                          getView().initAdapter(mainData);
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
