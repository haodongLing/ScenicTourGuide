package com.haodong.scenictourguide.location.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
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
import com.haodong.scenictourguide.common.ui.recycler.ItemType;
import com.haodong.scenictourguide.common.ui.recycler.MultipleViewHolder;
import com.haodong.scenictourguide.commonvh.AdViewHolder;
import com.haodong.scenictourguide.commonvh.LabelViewHolder;

import java.util.ArrayList;
import java.util.List;

public class ScenicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int ITEM_TYPE_HEAD = 0;
    private static final int ITEM_TYPE_AD = 1;
    private static final int ITEM_TYPE_LABEL = 2;
    private static final int ITEM_TYPE_CONTENT = 3;

    private Context mContext;
    private ArrayList<ScenicBean.ResultBean> dataArray;

    public ScenicListAdapter(Context context, ArrayList<ScenicBean.ResultBean> data) {
        this.mContext = context;
        this.dataArray = data;
    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return ITEM_TYPE_HEAD;
            case 1:
                return ITEM_TYPE_AD;
            case 2:
                return ITEM_TYPE_LABEL;
            default:
                return ITEM_TYPE_CONTENT;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view;
        switch (viewType) {
            case ITEM_TYPE_HEAD:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_scenic_header,
                        viewGroup, false);
                HeadViewHolder headViewHolder = new HeadViewHolder(view);
                return headViewHolder;
            case ITEM_TYPE_AD:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_ad,
                        viewGroup, false);
                AdViewHolder adViewHolder = new AdViewHolder(view);
                return adViewHolder;
            case ITEM_TYPE_LABEL:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_label,
                        viewGroup, false);
                LabelViewHolder labelViewHolder = new LabelViewHolder(view);
                return labelViewHolder;
            default:
                view = LayoutInflater.from(mContext).inflate(R.layout.item_list_scenic,
                        viewGroup, false);
                ContentViewHolder contentViewHolder = new ContentViewHolder(view);
                return contentViewHolder;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        getItemViewType(i);
        switch (i){
            case ITEM_TYPE_HEAD :
                break;
            case ITEM_TYPE_AD:
                break;
            case ITEM_TYPE_LABEL:
                break;
                default:
//                    int tempPosition
                    break;
        }

    }


    @Override
    public int getItemCount() {
        return dataArray.size()+3;
    }
}
