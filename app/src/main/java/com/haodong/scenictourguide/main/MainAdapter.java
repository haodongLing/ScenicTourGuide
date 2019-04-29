package com.haodong.scenictourguide.main;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haodong.scenictourguide.R;

import java.util.List;

/**
 * describe :
 * date on 2019/4/30
 * author linghailong
 * email 105354999@qq.com
 */
public class MainAdapter extends BaseQuickAdapter<MainData.MainDataInfo, BaseViewHolder> {
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .error(R.drawable.img_beijing1)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();

    public MainAdapter(int layoutResId, @Nullable List<MainData.MainDataInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MainData.MainDataInfo item) {
        helper.setText(R.id.main_item_name, item.getName());
        Glide.with(mContext)
                .load(item.getPortrait())
                .apply(RECYCLER_OPTIONS)
                .into((ImageView) helper.getView(R.id.main_item_portrait));
        helper.setText(R.id.main_item_title, item.getTitle());
        helper.setText(R.id.main_item_location, item.getLocation());
        helper.setText(R.id.main_item_content, item.getContent());
        helper.setText(R.id.main_item_date, item.getDate());
        RecyclerView recyclerView = helper.getView(R.id.item_main_pic_list);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        ImgAdapter imgAdapter = new ImgAdapter(R.layout.item_new, item.getPicUrlList());
        recyclerView.setAdapter(imgAdapter);
    }

     class ImgAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
        public ImgAdapter(int layoutResId, @Nullable List<String> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, String item) {
            Glide.with(this.mContext)
                    .load(item)
                    .apply(RECYCLER_OPTIONS)
                    .into((ImageView) helper.getView(R.id.item_new_img));
        }
    }

}
