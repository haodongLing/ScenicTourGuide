package com.haodong.scenictourguide.hotel;

import com.haodong.scenictourguide.common.net.RxRestClient;
import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.hotel.contract.HotelContract;
import com.haodong.scenictourguide.hotel.data.HotelBean;
import com.haodong.scenictourguide.hotel.data.HotelDataConverter;
import com.haodong.scenictourguide.location.LocationFragment;
import com.haodong.scenictourguide.location.data.LocationDataConverter;
import com.haodong.scenictourguide.location.data.ScenicBean;
import com.haodong.scenictourguide.location.data.UrlToos;
import com.haodong.scenictourguide.location.data.contractor.LocationContact;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * describe :
 * date on 2019/4/24
 * author linghailong
 * email 105354999@qq.com
 */
public class HotelPresenter extends BasePresenter<HotelFragment>implements HotelContract.Presenter {
    private HotelContract.View mView;
    public HotelPresenter(HotelFragment view) {
        super(view);
      this.mView=getView();
    }
    private String mLocation;

    @Override
    public void loadFirstPage(String location) {
        mLocation=location;

        String mPage=String.valueOf(1);
        String url= UrlToos.getHotelUrl(mLocation);
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .map(new Function<String,HotelBean>() {
                    @Override
                    public HotelBean apply(String s) throws Exception {
                        HotelBean hotelBean= (HotelBean) new HotelDataConverter().setJsonData(s).convert();
                        if (hotelBean!=null){
                            return hotelBean;
                        }
                        return new HotelBean();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HotelBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HotelBean hotelBean) {

                        if (mView != null) {
                            if (hotelBean.getHotelLists() != null) {
                                mView.showData(hotelBean);
                            } else {
                                mView.showError("加载失败");
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (mView!=null){
                            mView.showError("加载失败");
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
