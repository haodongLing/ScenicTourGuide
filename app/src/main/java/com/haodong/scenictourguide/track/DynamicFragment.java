package com.haodong.scenictourguide.track;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.app.fragments.PresenterFragment;
import com.haodong.scenictourguide.main.MainData;
import com.haodong.scenictourguide.main.MainDataManager;
import com.haodong.scenictourguide.track.contractor.DynamicContract;
import com.haodong.scenictourguide.track.dialog.BottomRemindDialog;
import com.haodong.scenictourguide.track.draghelper.ItemDragHelperCallback;
import com.haodong.scenictourguide.track.recycler.SlideImage;
import com.haodong.scenictourguide.track.recycler.SlideImageAdapter;
import com.haodong.scenictourguide.view.dialog.ProgressDialog;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.MyGlideEngine;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;

/**
 * describe :
 * date on 2019/4/13
 * author linghailong
 * email 105354999@qq.com
 */
public class DynamicFragment extends PresenterFragment<DynamicContract.Presenter> implements DynamicContract.view
        , View.OnClickListener, SlideImageAdapter.notifyImgDeleted {
    private ImageView mImgBack;
    private TextView mtvTitle, mTvFabu;
    private RecyclerView mRvImg;
    private EditText mEtvContent;
    private TextView mTvDate;
    private TextView mTvLocation;
    private LinearLayout mLayoutRemind;
    public static final int REQUEST_CODE_CHOOSE = 23;
    private boolean onBackClicked = false;
    private BottomRemindDialog mBottomRemindDialog;
    private SlideImageAdapter mSlideImgAdapter;
    private String intentFrom;
    private LinearLayout mLayoutTitle;
    private TextView tvTitle;
    private ProgressDialog mPregressDislog;
    @BindView(R.id.dynamic_tv_content)
    TextView tvContent;
    @BindView(R.id.dynamic_etv_title)
    EditText etvTitle;
    List<Uri> uriList = new ArrayList<>();
    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                /*显示dialog*/
                case 0:
                    break;
                /*隐藏dialog*/
                case 1:
                    if (mPregressDislog != null) {
                        mPregressDislog.dismiss();
                    }
                    break;
            }
        }
    };

    @Override
    public DynamicContract.Presenter initPresenter() {
        return new DynamicPresenter(this);
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_dynamic;
    }

    @Override
    protected void initArgs(Bundle bundle) {
        super.initArgs(bundle);
        if (bundle.getString("intentFrom") != null) {
            intentFrom = bundle.getString("intentFrom");
        }
    }

    @Override
    protected void initWidget(View root) {
        super.initWidget(root);
        mImgBack = root.findViewById(R.id.dynamic_back);
        mImgBack.setOnClickListener(this);
        mTvFabu = root.findViewById(R.id.dynamic_tv_fabu);
        mTvLocation = root.findViewById(R.id.dynamic_location);
        mTvLocation.setText(TourGuide.getConfiguration(ConfigKeys.PROVINCE));
        mTvDate = root.findViewById(R.id.dynamic_tv_date);
        mLayoutRemind = root.findViewById(R.id.dynamic_layout_remind);
        mRvImg = root.findViewById(R.id.dynamic_rv);
        mLayoutTitle = root.findViewById(R.id.dynamic_layout_title);
        tvTitle = root.findViewById(R.id.dynamic_title);
        /*设置时间*/
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINESE);
        mTvDate.setText(dateFormat.format(calendar.getTime()));
        if (intentFrom != null && intentFrom.contains("track")) {
            mLayoutTitle.setVisibility(View.VISIBLE);
            tvTitle.setText("编辑游记");
        }

        GridLayoutManager manager = new GridLayoutManager(getActivity(), 3);
        mRvImg.setLayoutManager(manager);
        ItemDragHelperCallback callback = new ItemDragHelperCallback() {
            @Override
            public boolean isLongPressDragEnabled() {
                // 长按拖拽打开
                return true;
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(mRvImg);

        /*onCLick*/
        mImgBack.setOnClickListener(this);
        mLayoutRemind.setOnClickListener(this);
        mTvFabu.setOnClickListener(this);
    }

    private void checkPermissions() {
        int hasReadStorage = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission
                        .READ_EXTERNAL_STORAGE);
        int hasWriteStorage = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (hasReadStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission
                    .READ_EXTERNAL_STORAGE}, 23);
        }
        if (hasWriteStorage != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission
                    .WRITE_EXTERNAL_STORAGE}, 23);
        }
    }

    public boolean checkPermissionWhileJump() {
        int hasReadStorage = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission
                        .READ_EXTERNAL_STORAGE);
        int hasWriteStorage = ContextCompat.checkSelfPermission(getActivity().getApplicationContext(),
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        return hasReadStorage == PackageManager.PERMISSION_GRANTED && hasWriteStorage == PackageManager
                .PERMISSION_GRANTED;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dynamic_back:
                onDestroy();
                break;
            case R.id.dynamic_layout_remind:
                if (checkPermissionWhileJump()) {
                    Matisse.from(DynamicFragment.this)
                            .choose(MimeType.ofOnlyImage())
                            .showSingleMediaType(true)
                            .countable(true)
                            .thumbnailScale(0.85f)
                            .theme(R.style.Matisse_Zhihu)
                            .countable(false)
                            .maxSelectable(9)
                            .originalEnable(true)
                            .imageEngine(new MyGlideEngine())
                            .forResult(REQUEST_CODE_CHOOSE);
                } else {
                    checkPermissions();
                }
                break;
            case R.id.dynamic_tv_fabu:
                MainData.MainDataInfo info = new MainData.MainDataInfo();
                info.setLocation(mTvLocation.getText().toString());
                info.setDate(mTvDate.getText().toString());
                info.setTitle(etvTitle.getText().toString());
//                info.setContent(mTV);
                info.setPortrait("http://ww1.sinaimg.cn/large/006fCF3Ply1g2jwwi1hkkj303c03c0sm.jpg");
                List<String> strings = new ArrayList<>();
                for (int i = 0; i < uriList.size(); i++) {
                    strings.add(uriList.get(i).toString());
                }
                info.setPicUrlList(strings);
                info.setContent(tvContent.getText().toString());
                info.setName("呼啦啦");
                showProgress();
                mPresenter.upLoad(info);
                Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE && resultCode == RESULT_OK) {
            if (data != null) {
                uriList = Matisse.obtainResult(data);
                List<String> stringList = Matisse.obtainPathResult(data);
                initAdapter(mPresenter.getSlideArray(uriList, stringList));
            } else {
                showRemind();
            }
            if (mSlideImgAdapter != null) {
                mSlideImgAdapter.setDataChanged(true);
            }
        }
    }

    private void initAdapter(ArrayList<SlideImage> slideImages) {
        if (mSlideImgAdapter == null) {
            mSlideImgAdapter = new SlideImageAdapter(getContext(), slideImages, this);
            mRvImg.setAdapter(mSlideImgAdapter);
        } else {
            mSlideImgAdapter.setSlideImageArr(slideImages);
            mSlideImgAdapter.notifyDataSetChanged();
        }
        showRemind();
    }

    private void showRemind() {
        if (mSlideImgAdapter == null || mSlideImgAdapter.getSlideImageArr() == null || mSlideImgAdapter
                .getSlideImageArr().size() == 0) {
            mLayoutRemind.setVisibility(View.VISIBLE);
        } else {
            mLayoutRemind.setVisibility(View.GONE);
        }
    }

    @Override
    public void onImgDeleted(int size) {
        if (size == 0) {
            showRemind();
        }
    }

    @Override
    public boolean onBackPressed() {
        onDestroy();
        return true;
    }

    @Override
    public void showProgress() {
        if (mPregressDislog == null) {
            mPregressDislog = new ProgressDialog(getContext());
            mPregressDislog.show();
        } else {
            mPregressDislog.show();
        }
    }

    @Override
    public void hideProgress() {
        mHandler.sendEmptyMessage(1);
    }

    @Override
    public void onDestroy() {
        mHandler.removeMessages(1);
        super.onDestroy();
    }
}
