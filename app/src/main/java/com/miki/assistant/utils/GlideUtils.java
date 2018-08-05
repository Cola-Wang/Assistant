package com.miki.assistant.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;

/**
 * 包名:      com.miki.assistant.utils
 * 文件名:     GlideUtils.java
 * 创建者:     王子豪
 * 创建时间:   2018/8/5 16:31
 * 描述:      Glide封装
 */

public class GlideUtils {

    //标准的加载方式
    public static void loadImageView(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }

    //居中的加载方式
    public static void loadImageViewCenterCrop(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).centerCrop().into(imageView);
    }

    //加载bitmap
    public static void loadBitmap(Context context, String url, final onSimpleTargetListener targetListener) {
        Glide.with(context).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                targetListener.onResourceReady(resource);
            }
        });
    }

    public interface onSimpleTargetListener {
        void onResourceReady(Bitmap resource);
    }
}
