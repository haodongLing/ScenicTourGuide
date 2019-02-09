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
import com.haodong.scenictourguide.common.ui.recycler.ItemType;
import com.haodong.scenictourguide.common.ui.recycler.MultipleViewHolder;
import com.haodong.scenictourguide.commonvh.AdViewHolder;
import com.haodong.scenictourguide.commonvh.LabelViewHolder;

import java.util.List;

public class ScenicListAdapter extends BaseQuickAdapter<ScenicBean.ResultBean, BaseViewHolder> {
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public ScenicListAdapter(int layoutResId, @Nullable List<ScenicBean.ResultBean> data) {
        super(layoutResId, data);
    }

    public ScenicListAdapter(@Nullable List<ScenicBean.ResultBean> data) {
        super(data);
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
    protected void convert(BaseViewHolder helper, ScenicBean.ResultBean item) {
       if (helper instanceof ContentViewHolder){
           helper.setText(R.id.item_scenit_list_title,item.getTitle());
           helper.setText(R.id.item_scenit_list_grade,item.getGrade());
           helper.setText(R.id.item_scenit_list_location,item.getAddress());
           helper.setText(R.id.item_scenit_list_price,item.getPrice_min());
           Glide.with(mContext)
                   .load(item.getImgurl())
                   .apply(RECYCLER_OPTIONS)
                   .into((ImageView) helper.getView(R.id.item_scenit_list_img));
       }
    }


}
