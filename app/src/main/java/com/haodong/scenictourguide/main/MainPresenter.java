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
public class MainPresenter extends BasePresenter<MainFragment>implements MainContract.Presenter {
    public static class TestBean{
        private String name;
        private String Age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getAge() {
            return Age;
        }

        public void setAge(String age) {
            Age = age;
        }
    }

    public MainPresenter(MainFragment view) {
        super(view);
    }

    @Override
    public void loadData() {
        TestBean testBean = new TestBean();
        testBean.setAge("12");
        testBean.setName("Tom");
        String json = JSON.toJSONString(testBean);
        Log.e("lhl", "loadData: "+json );
        RxRestClient.builder()
                .url("http://47.93.39.51/test")
                .raw(json)
                .build()
                .post()
                .subscribeOn(Schedulers.io())
                .map(new Function<String,String>() {
                    @Override
                    public String apply(String s) throws Exception {
                        return s;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String hotelBean) {
                        Log.e("lhl", "onNext: "+hotelBean );
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
