package com.bwie.wang.xiaomaipu.my.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

import java.lang.ref.WeakReference;

/**
 * date:2018/12/29.
 *
 * @author 王丙均
 */

public class GlideUtils {

    private static GlideUtils sGlideUtils;

    private static WeakReference<Context> mWeakReference;

    private final RequestManager mManager;

    /**
     * 将构造方法私有
     */
    private GlideUtils() {
        mManager = Glide.with(mWeakReference.get());
    }

    /**
     * 初始化Glide
     *
     * @param context 上下文
     */
    public static void init(Context context) {
        mWeakReference = new WeakReference<>(context);
    }

    /**
     * 双重锁单例模式
     *
     * @return GlideUtils对象
     */
    public static GlideUtils getInstance() {
        if (sGlideUtils == null) {
            synchronized (GlideUtils.class) {
                if (sGlideUtils == null) {
                    sGlideUtils = new GlideUtils();
                }
            }
        }
        return sGlideUtils;
    }

    /**
     * 加载图片
     *
     * @param imgUrl    图片地址
     * @param imageView 图片视图
     */
    public void loadImg(String imgUrl, ImageView imageView) {
        mManager.load(imgUrl)
                .into(imageView);
    }

    /**
     * 加载圆形图片
     *
     * @param imgUrl    图片地址
     * @param imageView 图片视图
     */
    public void loadCircleImg(String imgUrl, ImageView imageView) {
        mManager.load(imgUrl)
                .apply(new RequestOptions().transform(new CircleCrop()))
                .into(imageView);
    }

    /**
     * 加载圆角图片
     *
     * @param radiusSize 圆角大小，单位px
     * @param imgUrl     图片地址
     * @param imageView  图片视图
     */
    public void loadRadiusImg(int radiusSize, String imgUrl, ImageView imageView) {
        float density = mWeakReference.get()
                .getResources()
                .getDisplayMetrics().density;
        int radiusSizeDp = (int)(radiusSize * density + 0.5f);

        //Glide加载圆角图片，并且图片缩放全屏
        RequestOptions options = new RequestOptions().transforms(new CenterCrop(), new RoundedCorners(radiusSizeDp));

        mManager.load(imgUrl)
                .apply(options)
                .into(imageView);
    }
}
