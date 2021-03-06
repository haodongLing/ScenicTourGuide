package com.haodong.scenictourguide.location.data;

import android.support.annotation.Nullable;
import android.view.View;
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

import java.util.List;

public class ImgAdapter extends BaseQuickAdapter<ScenicBean.ContentlistBean.PicListBean,
        BaseViewHolder> {
    private List<ScenicBean.ContentlistBean.PicListBean> dataArr = null;

    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .error(R.drawable.ic_zanwu)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public ImgAdapter(int layoutResId, @Nullable List<ScenicBean.ContentlistBean.PicListBean> data
    ) {
        super(layoutResId, data);
        this.dataArr = data;
    }

    public ImgAdapter(@Nullable List<ScenicBean.ContentlistBean.PicListBean> data) {
        super(data);
        this.dataArr = data;
        init();
    }

    private void init() {
    }

    public ImgAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        view = getItemView(R.layout.item_attraction_img, parent);
        return new BaseViewHolder(view);
    }

    @Override
    protected void convert(BaseViewHolder helper, ScenicBean.ContentlistBean.PicListBean item) {
        String imgUrl = item.getPicUrl();
        if (imgUrl != null) {
            Glide.with(mContext)
                    .load(imgUrl)
                    .apply(RECYCLER_OPTIONS)
                    .into((ImageView) helper.getView(R.id.item_scenic_list_img));
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        convert(holder, dataArr.get(position));
    }
}
