package com.haodong.scenictourguide.hotel.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
 * date on 2019/4/25
 * author linghailong
 * email 105354999@qq.com
 */
public class HotelAdapter extends BaseQuickAdapter<HotelBean.HotelList, BaseViewHolder> {
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .error(R.drawable.img_beijing_small)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();
    public HotelAdapter(Context context,@Nullable List<HotelBean.HotelList> data) {
        super(data);
        mContext=context;
    }

    @Override
    protected void convert(BaseViewHolder helper, HotelBean.HotelList item) {
        if (item.getChineseName()!=null){
            helper.setText(R.id.item_hotel_list_title,item.getChineseName());
        }
        if (item.getAddress()!=null){
            helper.setText(R.id.item_hotel_list_location,item.getAddress());
        }
        if (item.getStar()!=0){
            helper.setText(R.id.item_hotel_list_star_name,item.getStar()+"星级");
        }
        if (item.getStarName()!=null){
            helper.setText(R.id.item_hotel_list_star_name,item.getStarName());
        }
        if (item.getPicture()!=null){
            Glide.with(mContext)
                    .load(item.getPicture())
                    .apply(RECYCLER_OPTIONS)
                    .into((ImageView) helper.getView(R.id.item_hotel_list_img));
        }

    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_hotel, parent,false);
        return new BaseViewHolder(view);
    }
}
