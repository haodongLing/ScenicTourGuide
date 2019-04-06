package com.haodong.scenictourguide.location.data;

import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.common.app.ConfigKeys;
import com.haodong.scenictourguide.common.app.TourGuide;
import com.haodong.scenictourguide.common.ui.recycler.ItemType;
import com.haodong.scenictourguide.commonvh.AdViewHolder;
import com.haodong.scenictourguide.commonvh.LabelViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ScenicListAdapter extends BaseQuickAdapter<ScenicBean.ContentlistBean, BaseViewHolder> {
    private List<ScenicBean.ContentlistBean> dataArr = null;
    private boolean mIsFirstIn = true;

    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public ScenicListAdapter(int layoutResId, @Nullable List<ScenicBean.ContentlistBean> data,
                             boolean isFirstIn) {
        super(layoutResId, data);
        this.dataArr = data;
        this.mIsFirstIn = isFirstIn;
    }

    public ScenicListAdapter(@Nullable List<ScenicBean.ContentlistBean> data, boolean isFirstIn) {
        super(data);
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
            case 1:
                return ItemType.AD;
            case 2:
                return ItemType.LABEL;
            default:
                return ItemType.CONTENT;
        }

    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case ItemType.HEAD:
                return new HeadViewHolder(getItemView(R.layout.item_scenic_header, parent));
            case ItemType.AD:
                return new AdViewHolder(getItemView(R.layout.item_ad, parent));
            case ItemType.LABEL:
                return new LabelViewHolder(getItemView(R.layout.item_label, parent));
            default:
                return new ContentViewHolder(getItemView(R.layout.item_list_scenic, parent));
        }
    }


    @Override
    protected void convert(BaseViewHolder helper, ScenicBean.ContentlistBean item) {
        if (helper instanceof HeadViewHolder) {
            helper.setText(R.id.tv_item_header_location, TourGuide.getConfiguration(ConfigKeys
                    .CITY));
            helper.addOnClickListener(R.id.layout_item_header_location);
        }
        if (helper instanceof ContentViewHolder) {
            helper.setText(R.id.item_scenit_list_title, item.getName());
            if (item.getAddress() != null) {
                helper.setText(R.id.item_scenit_list_location, item.getAddress());
            }
            if (item.getPrice() != null) {
                helper.setText(R.id.item_scenit_list_price, item.getPrice());
            }
            String imgUrl = item.getPicList().get(0).getPicUrl();
            if (imgUrl != null) {
                Glide.with(mContext)
                        .load(imgUrl)
                        .apply(RECYCLER_OPTIONS)
                        .into((ImageView) helper.getView(R.id.item_scenit_list_img));
            }


        }

    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

        if (position > 2) {
            convert(holder, mData.get(position - 2));
        }
    }


}
