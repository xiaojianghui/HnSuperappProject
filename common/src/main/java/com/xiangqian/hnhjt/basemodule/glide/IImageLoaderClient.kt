package com.xiangqian.hnhjt.basemodule.glide

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.xiangqian.hnhjt.basemodule.glide.listener.IImageLoaderListener
import java.io.File

interface IImageLoaderClient {

    fun init(context: Context?)

    fun destroy(context: Context?)

    fun getCacheDir(context: Context?): File?

    fun clearMemoryCache(context: Context?)

    fun clearDiskCache(context: Context?)

    fun getBitmapFromCache(context: Context?, url: String?): Bitmap?

//     void getBitmapFromCache(Context context, String url, IGetBitmapListener listener);

//     void displayImage(Context context, int resId, ImageView imageView);

    //     void getBitmapFromCache(Context context, String url, IGetBitmapListener listener);
    //     void displayImage(Context context, int resId, ImageView imageView);
    //加载普通网络图片
    fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    )

    fun displayGifImage(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    )

    fun displayImage(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    )

    fun displayImage(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        width: Int,
        height: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    )

    fun displayImage(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        width: Int,
        height: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    fun displayImage(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    )

    fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        width: Int,
        height: Int,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    //加载网络圆形图片
    fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCircle(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCircle(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCircle(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    //加载网络圆角图片
    fun displayImageRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        strategy: DiskCacheStrategy?
    )

    //加载网络圆角输入默认图
    fun displayImageRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        placeId: Int,
        errorId: Int,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        color: Int,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        color: Int,
        placeId: Int,
        errorId: Int,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean,
        strategy: DiskCacheStrategy?
    )

    //加载圆角图片,带默认图
    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        placeId: Int,
        errorId: Int,
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    )

    fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    //加载网络图片-模糊处理
    fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?
    )

    fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        color: Int,
        strategy: DiskCacheStrategy?
    )

    fun displayImageBlur(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?
    )

    fun displayImageBlur(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

    fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    )

    fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    )

}