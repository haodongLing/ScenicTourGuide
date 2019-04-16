package com.haodong.scenictourguide.mine.data;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haodong.scenictourguide.R;

import java.util.List;

/**
 * describe :
 * date on 2019/4/16
 * author linghailong
 * email 105354999@qq.com
 */
public class MineAdapter extends BaseQuickAdapter<MineItem, BaseViewHolder> {

    public MineAdapter(@Nullable List<MineItem> data, Context context) {
        super(data);
        mContext=context;
    }


    @Override
    protected void convert(BaseViewHolder helper, MineItem item) {
        helper.setImageResource(R.id.item_mine_img, item.getId());
        helper.setText(R.id.item_mine_tv, item.getString());

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_mine, parent, false);
        return new BaseViewHolder(view);
    }
}
