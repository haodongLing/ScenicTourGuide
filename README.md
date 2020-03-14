# ScenicTourGuide
毕设-retorfit，okhttp3,rxjava搭建可复用式地网络框架+mvp架构
### 介绍
1. 使用了mvp架构
2. 使用retorfit，okhttp3,rxjava搭建傻瓜式可复用式地网络框架
3. 由于接口收费的原因，暂时没做公开。

### 引言
终于毕业了，我把我的毕设贡献给大家吧，在实习阶段，公司框架主要都是使用的mvp+retrofit+okhttp3+rxjava的框架搭建的，我也根据自己的理解，搭建了一个项目作为毕设，关于mvp的优缺点我就不多说了，但是的确能让业务逻辑变得更加清晰。基于这个项目我就在此做一个抛砖引玉的效果吧，希望大家看了之后有自己的感悟。  

-----------------
### 界面欣赏


<img src="http://ww1.sinaimg.cn/large/006fCF3Ply1g4dqqwdwahj30f00qotdd.jpg" width = "200" height = "350" div align=left />
<img src="http://ww1.sinaimg.cn/large/006fCF3Ply1g4dqv0oabyj30f00qo42i.jpg" width = "200" height = "350" div align=center />
<img src="http://ww1.sinaimg.cn/large/006fCF3Ply1g4dqvy656gj30f00qodjw.jpg"  width = "200" height = "350" div align=right />  

 <img src="http://ww1.sinaimg.cn/large/006fCF3Ply1g4dqwyqs0zj30u01hc1am.jpg"  width = "200" height = "350" div align=left />  
 <img src="http://ww1.sinaimg.cn/large/006fCF3Ply1g4dqxngpbuj30f00qo75h.jpg" width = "200" height = "350" div align=center />
 <img src="http://ww1.sinaimg.cn/large/006fCF3Ply1g4dqycf5g6j30u01hcqu5.jpg" width = "200" height = "350" div align=right />
 <img src="http://ww1.sinaimg.cn/large/006fCF3Ply1g4dqzbyxr4j30f00qoq4o.jpg" width = "200" height = "350" div align=center />  

-------------
### 底层架构
整个项目采用了分层的理念整个底层架构在common包中。
![](http://ww1.sinaimg.cn/large/006fCF3Ply1g4dpmblefoj31hc0smdof.jpg) 
`view`层以及`presenter`层的基础接口在`BaseContract`中
```
public interface BaseContract {
    // 基本的界面职责
    interface View<T extends Presenter> {
        // 公共的：显示一个字符串错误
        void showError(String error);

        // 公共的：显示进度条
        void showLoading();

        // 支持设置一个Presenter
        void setPresenter(T presenter);
    }

    // 基本的Presenter职责
    interface Presenter {
        // 共用的开始触发
        void start();

        // 共用的销毁触发
        void destroy();
    }
}
```
`BasePresenter`继承自`BaseContract的Presenter接口`我这里只是进行了简单拓展，具体的扩展，大家可以根据自己的具体逻辑进行设定。
```java
package com.haodong.scenictourguide.common.presenter;

/**
 * describe : basePresenter
 * author linghailong
 * date on 2019/1/26
 * email 105354999@qq.com
 */
public class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter {
    private T mView;

    public BasePresenter(T view) {
        setView(view);
    }

    /**
     * 设置一个View，子类可以复写
     */
    @SuppressWarnings("unchecked")
    protected void setView(T view) {
        this.mView = view;
        this.mView.setPresenter(this);
    }

    /**
     * 给子类使用的获取View的操作
     * 不允许复写
     *
     * @return View
     */
    protected final T getView() {
        return mView;
    }

    @Override
    public void start() {
        // 开始的时候进行Loading调用
        T view = mView;
        if (isViewAttached()) {
            view.showLoading();
        }
    }
    public boolean isViewAttached() {
        return mView != null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void destroy() {
        T view = mView;
        mView = null;
        if (view != null) {
            // 把Presenter设置为NULL
            view.setPresenter(null);
        }
    }
}

```
而在View层，主要对Fragment以及Activity进行了简单封装。在activity中，借鉴了模板模式的思想，把常用的类都进行了编写。我这里写得比较生硬，大家可以使用动态代理的方式进行进一步拓展。
```java
package com.haodong.scenictourguide.common.app.activities;

import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.widget.convention.PlaceHolderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportActivity;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportActivityDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import static butterknife.ButterKnife.bind;

/**
 * describe : 基类Activity
 * author linghailong
 * date on 2019/1/26
 * email 105354999@qq.com
 */
public abstract class MyActivity extends AppCompatActivity implements ISupportActivity {
    private final SupportActivityDelegate mDelegate = new SupportActivityDelegate(this);
    protected PlaceHolderView mPlaceHolderView;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDelegate.onCreate(savedInstanceState);
        /*界面初始化*/
        initWindows();

        if (initArgs(getIntent().getExtras())) {
            // 得到界面Id并设置到Activity界面中
            int layId = getContentLayoutId();
            setContentView(layId);
            unbinder = ButterKnife.bind(this);
            initBefore();
            initWidget();
            initData();
        } else {
            finish();
        }
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDelegate.onPostCreate(savedInstanceState);
    }


    /**
     * 初始化窗口
     */
    protected void initWindows() {
    }

    /**
     * 初始化控件调用之前
     */
    protected void initBefore() {

    }

    /**
     * 初始化相关参数
     *
     * @param bundle 参数Bundle
     * @return 如果参数正确返回True，错误返回False
     */
    protected boolean initArgs(Bundle bundle) {
        return true;
    }

    /**
     * 得到当前界面的资源文件Id
     *
     * @return 资源文件Id
     */
    protected abstract int getContentLayoutId();

    /**
     * 初始化控件
     */
    protected void initWidget() {

    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        // 当点击界面导航返回时，Finish当前界面
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        mDelegate.onDestroy();
        super.onDestroy();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mDelegate.dispatchTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    @Override
    public void onBackPressed() {
        onBackPressedSupport();
    }

    @Override
    public void onBackPressedSupport() {
        mDelegate.onBackPressedSupport();
    }


    @Override
    public SupportActivityDelegate getSupportDelegate() {
        return mDelegate;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return mDelegate.extraTransaction();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return mDelegate.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        mDelegate.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return mDelegate.onCreateFragmentAnimator();
    }

    @Override
    public void post(Runnable runnable) {
        mDelegate.post(runnable);
    }

    public void loadRootFragment(int containerId, @NonNull ISupportFragment toFragment) {
        mDelegate.loadRootFragment(containerId, toFragment);

    }

    /**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }
    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getSupportFragmentManager(), fragmentClass);
    }
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        mDelegate.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }
    /**
     * show一个Fragment,hide一个Fragment ; 主要用于类似微信主页那种 切换tab的情况
     */
    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        mDelegate.showHideFragment(showFragment, hideFragment);
    }
    /**
     * 当Fragment根布局 没有 设定background属性时,
     * Fragmentation默认使用Theme的android:windowbackground作为Fragment的背景,
     * 可以通过该方法改变其内所有Fragment的默认背景。
     */
    public void setDefaultFragmentBackground(@DrawableRes int backgroundRes) {
        mDelegate.setDefaultFragmentBackground(backgroundRes);
    }
}

```
至于对Fragment的封装，在这里不得不介绍以为大佬编写的[Fragmention](https://github.com/YoKeyword/Fragmentation),如果大家感兴趣可以去看看。
```java
package com.haodong.scenictourguide.common.app.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.haodong.scenictourguide.common.app.activities.MyActivity;
import com.haodong.scenictourguide.common.widget.convention.PlaceHolderView;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.ExtraTransaction;
import me.yokeyword.fragmentation.ISupportFragment;
import me.yokeyword.fragmentation.SupportFragmentDelegate;
import me.yokeyword.fragmentation.SupportHelper;
import me.yokeyword.fragmentation.anim.DefaultHorizontalAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public abstract class MyFragment extends Fragment implements ISupportFragment {
    private final SupportFragmentDelegate DELEGATE = new SupportFragmentDelegate(this);
    private View mRootView = null;
    protected Unbinder mRootUnBinder;
    protected PlaceHolderView mPlaceHolderView;
    // 标示是否第一次初始化数据
    protected boolean mIsFirstInitData = true;

    public abstract Object setLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        final View rootView;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((int) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        } else {
            throw new ClassCastException("type of setLayout() must be int or View!");
        }
        mRootView = rootView;
        initWidget(mRootView);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mIsFirstInitData) {
            // 触发一次以后就不会触发
            mIsFirstInitData = false;
            // 触发
            onFirstInit();
        }

        // 当View创建完成后初始化数据
        initData();
    }

    /**
     * 初始化相关参数
     */
    protected void initArgs(Bundle bundle) {

    }

    /**
     * 初始化控件
     */
    protected void initWidget(View root) {
        mRootUnBinder = ButterKnife.bind(this, root);
    }

    /**
     * 初始化数据
     */
    protected void initData() {

    }

    /**
     * 当首次初始化数据的时候会调用的方法
     */
    protected void onFirstInit() {

    }

    /**
     * 返回按键触发时调用
     *
     * @return 返回True代表我已处理返回逻辑，Activity不用自己finish。
     * 返回False代表我没有处理逻辑，Activity自己走自己的逻辑
     */
    public boolean onBackPressed() {
        return false;
    }


    /**
     * 设置占位布局
     *
     * @param placeHolderView 继承了占位布局规范的View
     */
    public void setPlaceHolderView(PlaceHolderView placeHolderView) {
        this.mPlaceHolderView = placeHolderView;
    }

    @Override
    public void post(Runnable runnable) {
        DELEGATE.post(runnable);
    }

    protected FragmentActivity _mActivity;

    @Override
    public SupportFragmentDelegate getSupportDelegate() {
        return DELEGATE;
    }

    @Override
    public ExtraTransaction extraTransaction() {
        return DELEGATE.extraTransaction();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        initArgs(getArguments());
        DELEGATE.onAttach((Activity) context);
        _mActivity = DELEGATE.getActivity();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DELEGATE.onCreate(savedInstanceState);
    }

    @Override
    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
        return DELEGATE.onCreateAnimation(transit, enter, nextAnim);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        DELEGATE.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        DELEGATE.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        DELEGATE.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        DELEGATE.onPause();
    }

    @Override
    public void onDestroyView() {
        DELEGATE.onDestroyView();
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        mRootUnBinder.unbind();
        DELEGATE.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        DELEGATE.onHiddenChanged(hidden);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DELEGATE.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void enqueueAction(Runnable runnable) {
        DELEGATE.enqueueAction(runnable);
    }

    @Override
    public void onEnterAnimationEnd(Bundle savedInstanceState) {
        DELEGATE.onEnterAnimationEnd(savedInstanceState);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        DELEGATE.onLazyInitView(savedInstanceState);
    }

    @Override
    public void onSupportVisible() {
        DELEGATE.onSupportVisible();
    }

    @Override
    public void onSupportInvisible() {
        DELEGATE.onSupportInvisible();
    }

    @Override
    final public boolean isSupportVisible() {
        return DELEGATE.isSupportVisible();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return DELEGATE.onCreateFragmentAnimator();
    }

    @Override
    public FragmentAnimator getFragmentAnimator() {
        return DELEGATE.getFragmentAnimator();
    }

    @Override
    public void setFragmentAnimator(FragmentAnimator fragmentAnimator) {
        DELEGATE.setFragmentAnimator(fragmentAnimator);
    }

    @Override
    public boolean onBackPressedSupport() {
        return DELEGATE.onBackPressedSupport();
    }


    @Override
    public void setFragmentResult(int resultCode, Bundle bundle) {
        DELEGATE.setFragmentResult(resultCode, bundle);
    }


    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        DELEGATE.onFragmentResult(requestCode, resultCode, data);
    }


    @Override
    public void onNewBundle(Bundle args) {
        DELEGATE.onNewBundle(args);
    }

    @Override
    public void putNewBundle(Bundle newBundle) {
        DELEGATE.putNewBundle(newBundle);
    }

    public void start(ISupportFragment toFragment) {
        DELEGATE.start(toFragment);
    }

    public void start(final ISupportFragment toFragment, @LaunchMode int launchMode) {
        DELEGATE.start(toFragment, launchMode);
    }
    /**
     * 加载根Fragment, 即Activity内的第一个Fragment 或 Fragment内的第一个子Fragment
     *
     * @param containerId 容器id
     * @param toFragment  目标Fragment
     */
    public void loadRootFragment(int containerId, ISupportFragment toFragment) {
        DELEGATE.loadRootFragment(containerId, toFragment);
    }
    /**
     * 加载多个同级根Fragment
     */
    public void loadMultipleRootFragment(int containerId, int showPosition, ISupportFragment... toFragments) {
        DELEGATE.loadMultipleRootFragment(containerId, showPosition, toFragments);
    }
    /**
     * show一个Fragment,hide一个Fragment ;
     */
    public void showHideFragment(ISupportFragment showFragment, ISupportFragment hideFragment) {
        DELEGATE.showHideFragment(showFragment, hideFragment);
    }
    /**
     * Start the target Fragment and pop itself
     */
    public void startWithPop(ISupportFragment toFragment) {
        DELEGATE.startWithPop(toFragment);
    }
    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getFragmentManager(), fragmentClass);
    }

    /**
     * 获取栈内的fragment对象
     */
    public <T extends ISupportFragment> T findChildFragment(Class<T> fragmentClass) {
        return SupportHelper.findFragment(getChildFragmentManager(), fragmentClass);
    }


}

```
### 网络层
以上是mvp最基础的底层逻辑的搭建。这部分工作仅仅完成了对view层的封装，而如今的app中，网络层是不可或缺的。  
网络层时下最流行的无非是okhttp3+retrofit+rxjava，okhttp太强大了，在看了他的源码之后真的是受益匪浅。而retrofit是对OKhttp3请求方法的封装，源码也用到了很多的设计模式，比如动态代理，抽象工厂，适配器等等等等，目前还没有研究完，等之后研究完了也分享下心得。Rxjava的响应式编程是它的精髓，真的很好用，能完美地进行线程的切断调度。在子线程与主线程之间的调度过程中，至少可以避免使用handler。话不多说，先来看看代码吧。
1. 首先是创建一个简单工厂类，管理retrofit
```java
package com.haodong.scenictourguide.common.net;

import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.net.cache.CacheIntercepter;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public class RestCreator {
    private static final class OkHttpHolder {
        private static final int TIME_OUT = 60;
        private static final int CACHE_SIZE = 20 * 1024 * 1024;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder().connectTimeout
                (60, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addNetworkInterceptor(new CacheIntercepter())
                .cache(new Cache(TourGuide.getApplicationContext().getCacheDir(), CACHE_SIZE))
                .build();
    }

    /*单例模式的静态颞部类*/
    private static final class RetrofitHolder {

        /*baseAPI*/
//        private static final String BASE_URL = TourGuide.getConfiguration(ConfigKeys.API_HOST);

        /*建造者模式*/
        /*添加转换器*/
        private static final String BASE_URL = "http://www.baidu.com";
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder().baseUrl(BASE_URL)
                .client(OkHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static final class RxRestServiceHolder {
        private static final RxRestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RxRestService.class);
    }

    public static RxRestService getRxRestService() {
        return RxRestServiceHolder.REST_SERVICE;
    }

}

```
我不是直接用的接口进行的请求，我用的json转化工具是fastjson,当初也是头铁，听说fastjson比Gson效率更高，对retrofit有进行了一次封装，方便请求和转化。正好自己也在学习设计模式，也就拿来练练手了。
```java
package com.haodong.scenictourguide.common.net;

import android.content.Context;

import com.haodong.scenictourguide.common.ui.GuideLoader;
import com.haodong.scenictourguide.common.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public class RxRestClient {
    private final WeakHashMap<String,Object> PARAMS;
    private final String URL;
    private final RequestBody BODY;
    private final LoaderStyle LOADER_STYLE;
    private final File FILE;
    private final Context CONTEXT;

    public RxRestClient(Context context,WeakHashMap<String, Object> params, String url,
                        RequestBody body,
                         File file,LoaderStyle loaderStyle) {
        this.PARAMS = params;
        this.URL = url;
        this.BODY = body;
        this.LOADER_STYLE = loaderStyle;
        this.FILE = file;
        this.CONTEXT = context;
    }
    public static RxRestClientBuilder builder(){
        return new RxRestClientBuilder();
    }
    private Observable<String> request(HttpMethod method) {
        final RxRestService service = RestCreator.getRxRestService();
        Observable<String> observable = null;

        if (LOADER_STYLE != null) {
            GuideLoader.showLoading(CONTEXT, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;
            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;
            case UPLOAD:
                final RequestBody requestBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), FILE);
                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", FILE.getName(), requestBody);
                observable = service.upload(URL, body);
                break;
            default:
                break;
        }

        return observable;
    }
    public final Observable<String> get() {
        return request(HttpMethod.GET);
    }

    public final Observable<String> post() {
        if (BODY == null) {
            return request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.POST_RAW);
        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null!");
            }
            return request(HttpMethod.PUT_RAW);
        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload() {
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {
        return RestCreator.getRxRestService().download(URL, PARAMS);
    }
}
```
```
package com.haodong.scenictourguide.common.net;

import android.content.Context;


import com.haodong.scenictourguide.common.ui.LoaderStyle;

import java.io.File;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public final class RxRestClientBuilder {

    private final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    private String mUrl = null;
    private RequestBody mBody = null;
    private Context mContext = null;
    private LoaderStyle mLoaderStyle = null;
    private File mFile = null;

    RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        PARAMS.putAll(params);
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {
        PARAMS.put(key, value);
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }

    public final RxRestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClient build() {
        return new RxRestClient(mContext, PARAMS, mUrl, mBody, mFile, mLoaderStyle);
    }
}

```
最后是RestService
```java
package com.haodong.scenictourguide.common.net;

import java.util.WeakHashMap;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * describe :
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public interface RxRestService {
    @GET
    Observable<String> get(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @FormUrlEncoded
    @POST
    Observable<String> post(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @POST
    Observable<String> postRaw(@Url String url, @Body RequestBody body);

    @FormUrlEncoded
    @PUT
    Observable<String> put(@Url String url, @FieldMap WeakHashMap<String, Object> params);

    @PUT
    Observable<String> putRaw(@Url String url, @Body RequestBody body);

    @DELETE
    Observable<String> delete(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @Streaming
    @GET
    Observable<ResponseBody> download(@Url String url, @QueryMap WeakHashMap<String, Object> params);

    @Multipart
    @POST
    Observable<String> upload(@Url String url, @Part MultipartBody.Part file);
}

```
这样做的好处是restService可以不用写太多方法，而且比较通用。反正萝卜青菜各有所爱吧，仅仅给大家作为观赏。  
然后是自己建立了一个每局，封装了请求的方式：
```java
package com.haodong.scenictourguide.common.net;

/**
 * describe : 请求方法的枚举类
 * date on 2019/1/26
 * author linghailong
 * email 105354999@qq.com
 */
public enum HttpMethod {
    GET,
    POST,
    POST_RAW,
    PUT,
    PUT_RAW,
    DELETE,
    UPLOAD
}
```
网络层差不多就是这些东西了。 
接下来就是请求的过程了。我准备再另写一篇来进行介绍，具体请听下回分解。   



