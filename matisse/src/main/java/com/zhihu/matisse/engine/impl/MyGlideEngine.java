package com.zhihu.matisse.engine.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.request.RequestOptions;
import com.zhihu.matisse.R;
import com.zhihu.matisse.engine.ImageEngine;

/**
 * description:
 * author: linghailong
 * date: 2019/3/27
 */
public class MyGlideEngine implements ImageEngine {
    @Override
    public void loadThumbnail(Context context, int resize, Drawable placeholder, ImageView
            imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .placeholder(placeholder)//这里可自己添加占位图
                .error(R.drawable.error)//这里可自己添加出错图
                .override(resize, resize)
                .centerCrop();
        Glide.with(context)
                .asBitmap()  // some .jpeg files are actually gif
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadGifThumbnail(Context context, int resize, Drawable placeholder, ImageView
            imageView,
                                 Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .placeholder(placeholder)//这里可自己添加占位图
                .error(R.drawable.error)//这里可自己添加出错图
                .override(resize, resize);
        Glide.with(context)
                .asBitmap()
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri uri) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public void loadPreviewImage(Context context, int resizeX, int resizeY, ImageView imageView,
                                 Uri uri) {
        RequestOptions options = new RequestOptions()
                .fitCenter()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(uri)
                .apply(options)
                .into(imageView);

    }

    public void loadGalleryImage(Context context, int resizeX, int resizeY, ImageView imageView,
                                 Uri uri) {
        RequestOptions options = new RequestOptions()
                .centerCrop()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH);
        Glide.with(context)
                .load(uri)
                .apply(options)
                .into(imageView);
    }


    @Override
    public void loadGifImage(Context context, int resizeX, int resizeY, ImageView imageView, Uri
            uri) {
        RequestOptions options = new RequestOptions()
                .override(resizeX, resizeY)
                .priority(Priority.HIGH)
                .fitCenter();
        Glide.with(context)
                .asGif()
                .load(uri)
                .apply(options)
                .into(imageView);
    }

    @Override
    public boolean supportAnimatedGif() {
        return true;
    }


}
