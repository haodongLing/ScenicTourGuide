package com.haodong.scenictourguide.location.data;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haodong.scenictourguide.DataTools;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.ui.recycler.ItemType;
import com.haodong.scenictourguide.commonvh.AdViewHolder;
import com.haodong.scenictourguide.commonvh.LabelViewHolder;
import com.haodong.scenictourguide.location.AttractionsActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.HEAD;

import static com.blankj.utilcode.util.ActivityUtils.startActivity;
import static com.haodong.scenictourguide.location.IntentKeys.INTENT_KEY_DATA;

public class ScenicListAdapter extends BaseQuickAdapter<ScenicBean.ContentlistBean,
        BaseViewHolder> implements BaseQuickAdapter.OnItemClickListener {
    private List<ScenicBean.ContentlistBean> dataArr = null;
    private boolean mIsFirstIn = true;
    private Context mCOntext;

    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .error(R.drawable.ic_zanwu)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public ScenicListAdapter(int layoutResId, @Nullable List<ScenicBean.ContentlistBean> data,
                             boolean isFirstIn) {
        super(layoutResId, data);
        this.setOnItemClickListener(this);
        this.dataArr = data;
        this.mIsFirstIn = isFirstIn;
    }

    public ScenicListAdapter( @Nullable List<ScenicBean.ContentlistBean> data, boolean isFirstIn) {
        super(data);
        this.setOnItemClickListener(this);
        this.dataArr = data;
        this.mIsFirstIn = isFirstIn;
        init();
    }

    private void init() {
    }

    public ScenicListAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected int getDefItemViewType(int position) {
        switch (position) {
            case 0:
                return ItemType.HEAD;
            default:
                return ItemType.CONTENT;
        }

    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        View view=null;
        if (viewType== ItemType.HEAD){
            view=getItemView(R.layout.item_scenic_header, parent);
        }else {
          view=getItemView(R.layout.item_list_scenic, parent);
        }
        return new HeadViewHolder(view);
    }


    @Override
    protected void convert(BaseViewHolder helper, ScenicBean.ContentlistBean item) {
        if (helper.getItemViewType()==ItemType.HEAD) {
//            if (TourGuide.getConfiguration(ConfigKeys.CITY)!=null)
            helper.setText(R.id.tv_item_header_location, "北京");
            helper.addOnClickListener(R.id.layout_item_header_location);
            helper.setText(R.id.item_scenit_list_title, item.getName());
            if (item.getAddress() != null) {
                helper.setText(R.id.item_scenit_list_location, item.getAddress());
            }
            if (item.getPrice() != null) {
                helper.setText(R.id.item_scenit_list_price, item.getPrice()+"起");
            }
            String imgUrl = item.getPicList().get(0).getPicUrl();
            if (imgUrl != null) {
                Glide.with(mContext)
                        .load(imgUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.item_scenit_list_img));
            }
        } else if (helper.getItemViewType()==ItemType.CONTENT) {
            helper.setText(R.id.item_scenit_list_title, item.getName());
            if (item.getAddress() != null) {
                helper.setText(R.id.item_scenit_list_location, item.getAddress());
            }
            if (item.getPrice() != null) {
                helper.setText(R.id.item_scenit_list_price, item.getPrice());
            }
            if (item.getPicList() != null && item.getPicList().size() > 0) {
                String imgUrl = item.getPicList().get(0).getPicUrl();
                if (imgUrl != null) {
                    Glide.with(mContext)
                            .load(imgUrl)
                            .apply(RECYCLER_OPTIONS)
                            .into((ImageView) helper.getView(R.id.item_scenit_list_img));
                }

            }


        }

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, mData.get(position));
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        Intent intent=new Intent(mContext,AttractionsActivity.class);
//        intent.putExtra(INTENT_KEY_DATA,mData.get(position));
        startActivity(intent);
        DataTools.getInstance().setContentListBean(mData.get(position));
    }
}
