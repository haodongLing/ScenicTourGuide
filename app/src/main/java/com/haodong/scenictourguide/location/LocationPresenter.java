package com.haodong.scenictourguide.location;

import com.haodong.scenictourguide.common.net.RxRestClient;
import com.haodong.scenictourguide.common.presenter.BasePresenter;
import com.haodong.scenictourguide.location.data.contractor.LocationContact;
import com.haodong.scenictourguide.location.data.LocationDataConverter;
import com.haodong.scenictourguide.location.data.ScenicBean;
import com.haodong.scenictourguide.location.data.UrlToos;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class    LocationPresenter extends BasePresenter<LocationFragment> implements
        LocationContact.Presenter {
    private String mLocation;
    private LocationContact.View mView;

    LocationPresenter(LocationFragment view) {
        super(view);
        this.mView=getView();
    }
    @Override
    public void loadFirstPage(int page, String location) {
        mLocation=location;

        String mPage=String.valueOf(page);
        String url= UrlToos.getUrl(mLocation,mPage);
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .map(new Function<String,ScenicBean>() {
                    @Override
                    public ScenicBean apply(String s) throws Exception {
                        ScenicBean scenicBean= (ScenicBean) new LocationDataConverter().setJsonData(s).convert();
                        if (scenicBean!=null){
                            return scenicBean;
                        }
                        return new ScenicBean();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScenicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicBean scenicBean) {
                        if (mView!=null){
                            if (scenicBean.getContentlist()!=null){
                                mView.showData(scenicBean);
                                mView.showToolBar();
                            }else {
                                mView.showError("加载失败");
                                mView.showToolBar();
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

    @Override
    public void loadMorePage(int i) {
        String mPage=String.valueOf(i);
        String url= UrlToos.getUrl(mLocation,mPage);
        RxRestClient.builder()
                .url(url)
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .map(new Function<String,ScenicBean>() {
                    @Override
                    public ScenicBean apply(String s) throws Exception {
                        ScenicBean scenicBean= (ScenicBean) new LocationDataConverter().setJsonData(s).convert();
                        if (scenicBean!=null){
                            return scenicBean;
                        }
                        return new ScenicBean();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ScenicBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ScenicBean scenicBean) {
                        if (mView!=null){
                            if (scenicBean.getContentlist()!=null){
                                mView.showData(scenicBean);
                                mView.showToolBar();
                            }else {
                                mView.showError("加载失败");
                                mView.showToolBar();
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
    @Override
    public void onRefresh() {

    }
}
