package com.xiangqian.hnhjt.basemodule.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.Transformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.xiangqian.hnhjt.basemodule.R
import com.xiangqian.hnhjt.basemodule.glide.listener.IImageLoaderListener
import com.xiangqian.hnhjt.basemodule.glide.tranform.BlurTransformation
import com.xiangqian.hnhjt.basemodule.glide.tranform.RoundRadiusTransform
import jp.wasabeef.glide.transformations.ColorFilterTransformation
import java.io.File

class GlideImageLoaderClient : IImageLoaderClient {
    override fun init(context: Context?) {}

    override fun destroy(context: Context?) {
        clearMemoryCache(context)
    }

    override fun getCacheDir(context: Context?): File? {
        return Glide.getPhotoCacheDir(context!!)!!
    }

    override fun clearMemoryCache(context: Context?) {
        Glide.get(context!!).clearMemory()
    }

    override fun clearDiskCache(context: Context?) {
        object : AsyncTask<Void?, Void?, Void?>() {
            override fun doInBackground(vararg p0: Void?): Void? {
                //必须在子线程中  This method must be called on a background thread.
                Glide.get(context!!).clearDiskCache()
                return null
            }
        }
    }

    override fun getBitmapFromCache(context: Context?, url: String?): Bitmap? {
        return null
    }

    /**
     * 普通加载网络图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    override fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        displayImage(context, url, imageView, strategy, null)
    }

    override fun displayGifImage(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        var strategy = strategy
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty)
        if (null == strategy) {
            strategy = DiskCacheStrategy.AUTOMATIC
        }
        Glide.with(context!!).asGif().load(url).apply(requestOptions).diskCacheStrategy(strategy!!)
            .into(imageView!!)
    }

    override fun displayImage(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeId).error(errorId)
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImage(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        width: Int,
        height: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeId).error(errorId)
        displayImage(context, url, imageView, requestOptions, width, height, strategy, null)
    }

    override fun displayImage(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        width: Int,
        height: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeId).error(errorId)
        displayImage(
            context,
            url,
            imageView,
            requestOptions,
            width,
            height,
            strategy,
            loaderListener
        )
    }

    override fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty)
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }

    /**
     * 通过自定义 RequestOptions 加载图片
     * 可以设置placeholder占位图，加载错误图error
     *
     * @param context
     * @param url
     * @param imageView
     * @param requestOptions
     */
    override fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        var strategy = strategy
        if (null == strategy) {
            strategy = DiskCacheStrategy.AUTOMATIC
        }
        Glide.with(context!!).load(url).apply(requestOptions!!).diskCacheStrategy(strategy!!)
            .listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    loaderListener?.onLoadingFailed(url, imageView, e)
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    loaderListener?.onLoadingComplete(url, imageView, resource)
                    return false
                }
            }).into(imageView!!)
    }

    /**
     * 通过自定义 RequestOptions 加载图片
     * 可以设置placeholder占位图，加载错误图error
     *
     * @param context
     * @param url
     * @param imageView
     * @param requestOptions
     */
    override fun displayImage(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    ) {
        var strategy = strategy
        if (null == strategy) {
            strategy = DiskCacheStrategy.AUTOMATIC
        }
        Glide.with(context!!).load(url).apply(requestOptions!!).diskCacheStrategy(strategy!!)
            .into(imageView!!)
    }

    override fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        width: Int,
        height: Int,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        var strategy = strategy
        if (null == strategy) {
            strategy = DiskCacheStrategy.AUTOMATIC
        }
        Glide.with(context!!).load(url).apply(requestOptions!!).diskCacheStrategy(strategy!!)
            .override(width).override(height).listener(object : RequestListener<Drawable?> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any,
                    target: Target<Drawable?>,
                    isFirstResource: Boolean
                ): Boolean {
                    if (null != loaderListener) {
                        loaderListener.onLoadingFailed(url, imageView, e)
                    }
                    return false
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any,
                    target: Target<Drawable?>,
                    dataSource: DataSource,
                    isFirstResource: Boolean
                ): Boolean {
                    if (null != loaderListener) {
                        loaderListener.onLoadingComplete(url, imageView, resource)
                    }
                    return false
                }
            }).into(imageView!!)
    }

    /**
     * 加载圆形图片
     *
     * @param context
     * @param url
     * @param imageView
     */
    override fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty).circleCrop()
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCircle(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty).circleCrop()
        displayImage(context, url, imageView, requestOptions, strategy)
    }

    override fun displayImageCircle(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeId).error(errorId).circleCrop()
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCircle(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeId).error(errorId).circleCrop()
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }

    override fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty).circleCrop()
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }

    override fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }

    /**
     * 加载圆角图片，四个角都设置
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius
     */
    override fun displayImageRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        strategy: DiskCacheStrategy?
    ) {
        displayImageCustomRound(context, url, imageView, radius, true, true, true, true, strategy)
    }

    override fun displayImageRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        placeId: Int,
        errorId: Int,
        strategy: DiskCacheStrategy?
    ) {
        displayImageCustomRound(
            context,
            url,
            imageView,
            radius,
            placeId,
            errorId,
            true,
            true,
            true,
            true,
            strategy
        )
    }

    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(true, true, true, true)
        requestOptions?.transform(roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        color: Int,
        strategy: DiskCacheStrategy?
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(true, true, true, true)
        val requestOptions = RequestOptions()
        requestOptions.transform(ColorFilterTransformation(color), roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        color: Int,
        placeId: Int,
        errorId: Int,
        strategy: DiskCacheStrategy?
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(true, true, true, true)
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeId).error(errorId)
            .transform(ColorFilterTransformation(color), roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(true, true, true, true)
        requestOptions?.transform(roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }

    /**
     * 加载圆角图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param radius      角度
     * @param leftTop     左上角是否设置radius
     * @param rightTop    右上角是否设置radius
     * @param leftBottom  左下角是否设置radius
     * @param rightBottom 右下角是否设置radius
     */
    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean,
        strategy: DiskCacheStrategy?
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom)
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty)
            .error(R.mipmap.empty).transform(roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCustomRound(
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
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom)
        val requestOptions = RequestOptions()
        requestOptions.placeholder(placeId).error(errorId).transform(roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCustomRound(
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
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom)
        requestOptions?.transform(roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageCustomRound(
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
    ) {
        val roundRadiusTransform = RoundRadiusTransform(radius)
        roundRadiusTransform.setNeedCorner(leftTop, rightTop, leftBottom, rightBottom)
        requestOptions?.transform(roundRadiusTransform)
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }

    /**
     * 模糊图片
     *
     * @param context
     * @param url
     * @param imageView
     * @param blurRadius
     */
    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?
    ) {
        var blurRadius = blurRadius
        if (blurRadius < 0) {
            blurRadius = 0
        } else if (blurRadius > 25) {
            blurRadius = 25
        }
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty)
            .transform(BlurTransformation(context, null, blurRadius, 0))
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        color: Int,
        strategy: DiskCacheStrategy?
    ) {
        var blurRadius = blurRadius
        if (blurRadius < 0) {
            blurRadius = 0
        } else if (blurRadius > 25) {
            blurRadius = 25
        }
        val requestOptions = RequestOptions()
        requestOptions.centerCrop()
            .transform(
                ColorFilterTransformation(color),
                BlurTransformation(context, null, blurRadius, 3)
            )
        //        requestOptions.transform(new BlurTransformation(context,10,4));
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageBlur(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?
    ) {
        var blurRadius = blurRadius
        if (blurRadius < 0) {
            blurRadius = 0
        } else if (blurRadius > 25) {
            blurRadius = 25
        }
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty)
            .transform(BlurTransformation(context, null, blurRadius, 0))
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageBlur(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        var blurRadius = blurRadius
        if (blurRadius < 0) {
            blurRadius = 0
        } else if (blurRadius > 25) {
            blurRadius = 25
        }
        val requestOptions = RequestOptions()
        requestOptions.placeholder(R.mipmap.empty).error(R.mipmap.empty)
            .transform(BlurTransformation(context, null, blurRadius, 0))
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }

    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    ) {
        var blurRadius = blurRadius
        if (blurRadius < 0) {
            blurRadius = 0
        } else if (blurRadius > 25) {
            blurRadius = 25
        }
        requestOptions?.transform(BlurTransformation(context, null, blurRadius, 0))
        displayImage(context, url, imageView, requestOptions, strategy, null)
    }

    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        var blurRadius = blurRadius
        if (blurRadius < 0) {
            blurRadius = 0
        } else if (blurRadius > 25) {
            blurRadius = 25
        }
        requestOptions?.transform(BlurTransformation(context, null, blurRadius, 0))
        displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
    }


    fun requestOptionsTransform(
        placeholderResId: Int,
        errorResId: Int,
        transformation: Transformation<Bitmap>
    ): RequestOptions {
        return RequestOptions()
            .placeholder(placeholderResId)
            .error(errorResId).transform(transformation)
    }
}