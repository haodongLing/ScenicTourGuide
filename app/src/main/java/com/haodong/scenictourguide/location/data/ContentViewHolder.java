package com.haodong.scenictourguide.location.data;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseViewHolder;
import com.haodong.scenictourguide.R;

public class ContentViewHolder extends RecyclerView.ViewHolder {
    private ImageView img;
    private TextView title;
    private TextView grade;
    private TextView address;
    private TextView price_min;

    public ContentViewHolder(View view) {
        super(view);
        img = view.findViewById(R.id.item_scenit_list_img);
        title = view.findViewById(R.id.item_scenit_list_title);
        grade = view.findViewById(R.id.item_scenit_list_grade);
        address = view.findViewById(R.id.item_scenit_list_location);
        price_min = view.findViewById(R.id.item_scenit_list_price);
    }
}
