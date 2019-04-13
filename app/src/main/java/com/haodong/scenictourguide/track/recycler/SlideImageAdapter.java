package com.haodong.scenictourguide.track.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.haodong.scenictourguide.R;
import com.haodong.scenictourguide.track.draghelper.OnDragVHListener;
import com.haodong.scenictourguide.track.draghelper.OnItemMoveListener;

import java.util.ArrayList;

/**
 * description:
 * author: linghailong
 * date: 2019/3/22
 */
public class SlideImageAdapter extends RecyclerView.Adapter<SlideImageAdapter.SlideViewHolder>
        implements OnItemMoveListener {
    //设置图片加载策略
    private static final RequestOptions RECYCLER_OPTIONS =
            new RequestOptions()
                    .error(R.drawable.ic_zanwu)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .dontAnimate();
    private Context mContext;
    private notifyImgDeleted mNotifyImgDeletedLis;
    private ArrayList<SlideImage> mSlideImageArr = new ArrayList<>();
    /*如果data改变过，记录状态*/
    private boolean isDataChanged = false;

    public boolean isDataChanged() {
        return isDataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        isDataChanged = dataChanged;
    }


    public SlideImageAdapter(Context mContext, ArrayList<SlideImage> slideImageArr, notifyImgDeleted notifyImgDeleted) {
        this.mContext = mContext;
        this.mSlideImageArr = slideImageArr;
        this.mNotifyImgDeletedLis = notifyImgDeleted;
    }

    public ArrayList<SlideImage> getSlideImageArr() {
        return mSlideImageArr;
    }

    public void setSlideImageArr(ArrayList<SlideImage> slideImageArr) {
        if (mSlideImageArr.size() != 0) {
            mSlideImageArr.clear();
        }
        this.mSlideImageArr = slideImageArr;
    }

    @NonNull
    @Override
    public SlideViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.slide_gallery, viewGroup, false);
        return new SlideViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SlideViewHolder slideViewHolder, final int position) {
        slideViewHolder.imgDelete.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mSlideImageArr.remove(position);
                setDataChanged(true);
                notifyDataSetChanged();
                mNotifyImgDeletedLis.onImgDeleted(mSlideImageArr.size());
            }
        });
        Glide.with(mContext)
                .load(mSlideImageArr.get(position).getPath()) // 加载路径
                .apply(RECYCLER_OPTIONS)
                .into(slideViewHolder.imgPreview);
    }

    @Override
    public int getItemCount() {
        return mSlideImageArr.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        setDataChanged(true);
        updateData(fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    class SlideViewHolder extends RecyclerView.ViewHolder implements OnDragVHListener {
        private ImageView imgDelete;
        private ImageView imgPreview;
        private ImageView imgShade;

        public SlideViewHolder(@NonNull View itemView) {
            super(itemView);
            imgDelete = itemView.findViewById(R.id.slide_item_delete);
            imgPreview = itemView.findViewById(R.id.slide_item_image);
            imgShade = itemView.findViewById(R.id.slide_item_shade);
        }

        @Override
        public void onItemSelected() {
            imgShade.setVisibility(View.VISIBLE);
        }

        @Override
        public void onItemFinish() {
            imgShade.setVisibility(View.INVISIBLE);
        }
    }

    /*移动后，data改变，重新记录data的位置*/
    private void updateData(int formPosition, int toPosition) {
        int size = mSlideImageArr.size();
        if (formPosition < toPosition) {
            /*所有元素向前移动*/
            SlideImage fromImage = mSlideImageArr.get(formPosition);
            for (int i = formPosition; i < toPosition; i++) {
                SlideImage slideImage = mSlideImageArr.get(i + 1);
                mSlideImageArr.set(i, slideImage);
            }
            mSlideImageArr.set(toPosition, fromImage);
        } else {
            /*>所有元素往后挪动*/
            SlideImage fromImage = mSlideImageArr.get(formPosition);
            for (int i = formPosition; i > toPosition; i--) {
                SlideImage slideImage = mSlideImageArr.get(i - 1);
                mSlideImageArr.set(i, slideImage);
            }
            mSlideImageArr.set(toPosition, fromImage);
        }
    }
    public interface notifyImgDeleted {
        void onImgDeleted(int size);
    }

}
