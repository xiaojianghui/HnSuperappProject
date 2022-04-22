package com.xiangqian.hnhjt.basemodule.glide

import android.content.Context
import android.graphics.Bitmap
import android.widget.ImageView
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.xiangqian.hnhjt.basemodule.glide.listener.IImageLoaderListener
import java.io.File

class ImageLoaderUtils : IImageLoaderClient {
    @Volatile
    private  var mImageLoaderUtils: ImageLoaderUtils? = null
    private var client: IImageLoaderClient? = null


    fun getInstance(): ImageLoaderUtils {
        if (mImageLoaderUtils == null) {
            synchronized(ImageLoaderUtils::class.java) {
                if (mImageLoaderUtils == null) {
                    mImageLoaderUtils = ImageLoaderUtils()
                }
            }
        }
        return mImageLoaderUtils!!
    }

    init {
        client = GlideImageLoaderClient()
    }
    override fun init(context: Context?) {

    }


    /**
     * 销毁
     *
     * @param context
     */
    override fun destroy(context: Context?) {
        if (client != null) {
            client!!.destroy(context)
            client = null
        }
        mImageLoaderUtils = null
    }

    override fun getCacheDir(context: Context?): File? {
        return if (client != null) {
            client!!.getCacheDir(context)
        } else null
    }

    /**
     * 清除内存缓存
     *
     * @param context
     */
    override fun clearMemoryCache(context: Context?) {
        if (client != null) {
            client!!.clearMemoryCache(context)
        }
    }

    /**
     * 清除硬盘缓存
     *
     * @param context
     */
    override fun clearDiskCache(context: Context?) {
        if (client != null) {
            client!!.clearDiskCache(context)
        }
    }

    override fun getBitmapFromCache(context: Context?, url: String?): Bitmap? {
        return null
    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param url           图片地址
     * @param imageView
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
     * DiskCacheStrategy.NONE 不使用磁盘缓存
     * DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
     * DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
     * DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
     */
    override fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImage(context, url, imageView, strategy)
        }
    }

    override fun displayGifImage(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayGifImage(context, url, imageView, strategy)
        }
    }

    /**
     * 加载网络图片
     *
     * @param context
     * @param url           图片地址
     * @param placeId       占位图id
     * @param errorId       加载图片失败时显示的图片id
     * @param imageView
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
     * DiskCacheStrategy.NONE 不使用磁盘缓存
     * DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
     * DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
     * DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
     */
    override fun displayImage(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImage(context, url, placeId, errorId, imageView, strategy)
        }
    }

    /**
     * 加载指定大小的网络图片
     *
     * @param context
     * @param url           图片地址
     * @param placeId       占位图id
     * @param errorId       加载图片失败时显示的图片id
     * @param width         图片宽度
     * @param height        图片高度
     * @param imageView
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
     * DiskCacheStrategy.NONE 不使用磁盘缓存
     * DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
     * DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
     * DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
     */
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
        if (client != null && context != null) {
            client!!.displayImage(
                context,
                url,
                placeId,
                errorId,
                width,
                height,
                imageView,
                strategy
            )
        }
    }

    /**
     * 加载指定大小的网络图片带 --监听方法
     *
     * @param context
     * @param url            图片地址
     * @param placeId        占位图id
     * @param errorId        加载图片失败时显示的图片id
     * @param width          图片宽度
     * @param height         图片高度
     * @param imageView
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
     * DiskCacheStrategy.NONE 不使用磁盘缓存
     * DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
     * DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
     * DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
     * @param loaderListener 监听回调对象
     */
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
        if (client != null && context != null) {
            client!!.displayImage(
                context,
                url,
                placeId,
                errorId,
                width,
                height,
                imageView,
                strategy,
                loaderListener
            )
        }
    }

    /**
     * 加载网络图片 -- 带加载监听方法
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * DiskCacheStrategy.ALL 使用DATA和RESOURCE缓存远程数据，仅使用RESOURCE来缓存本地数据。
     * DiskCacheStrategy.NONE 不使用磁盘缓存
     * DiskCacheStrategy.DATA 在资源解码前就将原始数据写入磁盘缓存
     * DiskCacheStrategy.RESOURCE 在资源解码后将数据写入磁盘缓存，即经过缩放等转换后的图片资源。
     * DiskCacheStrategy.AUTOMATIC 根据原始图片数据和资源编码策略来自动选择磁盘缓存策略。
     * @param loaderListener 监听回调对象
     */
    override fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImage(context, url, imageView, strategy, loaderListener)
        }
    }

    /**
     * 加载网络图片 -- 带加载监听方法和自定义参数
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
    override fun displayImage(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImage(context, url, imageView, requestOptions, strategy, loaderListener)
        }
    }

    override fun displayImage(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImage(context, url, imageView, requestOptions, strategy)
        }
    }

    /**
     * 加载指定大小的网络图片 -- 带加载监听方法和自定义参数
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param width          图片宽度
     * @param height         图片高度
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
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
        if (client != null && context != null) {
            client!!.displayImage(
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
    }

    /**
     * 加载网络圆形图片
     *
     * @param context
     * @param url           图片地址
     * @param imageView
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     */
    override fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCircle(context, url, imageView, strategy)
        }
    }

    override fun displayImageCircle(
        context: Context?,
        url: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCircle(context, url, imageView, strategy)
        }
    }

    /**
     * 加载网络圆形图片--传placeId和errorId
     *
     * @param context
     * @param url           图片地址
     * @param placeId       占位图id
     * @param errorId       加载图片失败时显示的图片id
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param imageView
     */
    override fun displayImageCircle(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCircle(context, url, placeId, errorId, imageView, strategy)
        }
    }

    /**
     * 加载网络圆形图片--传placeId和errorId 带监听方法
     *
     * @param context
     * @param url            图片地址
     * @param placeId        占位图id
     * @param errorId        加载图片失败时显示的图片id
     * @param imageView
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
    override fun displayImageCircle(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCircle(
                context,
                url,
                placeId,
                errorId,
                imageView,
                strategy,
                loaderListener
            )
        }
    }

    /**
     * 加载网络圆形图片--带监听加载方法
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
    override fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCircle(context, url, imageView, strategy, loaderListener)
        }
    }

    /**
     * 加载网络圆形图片--带监听加载方法和自定义参数
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
    override fun displayImageCircle(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCircle(
                context,
                url,
                imageView,
                requestOptions,
                strategy,
                loaderListener
            )
        }
    }

    /**
     * 加载圆角网络图片---四个角都设置
     *
     * @param context
     * @param url           图片地址
     * @param imageView
     * @param radius        圆角角度
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     */
    override fun displayImageRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageRound(context, url, imageView, radius, strategy)
        }
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
        if (client != null && context != null) {
            client!!.displayImageRound(context, url, imageView, radius, placeId, errorId, strategy)
        }
    }

    /**
     * 加载圆角网络图片 --- 四个角都设置和自定义参数
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param radius         圆角角度
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     */
    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCustomRound(
                context,
                url,
                imageView,
                radius,
                requestOptions,
                strategy
            )
        }
    }

    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        color: Int,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCustomRound(context, url, imageView, radius, color, strategy)
        }
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
        if (client != null && context != null) {
            client!!.displayImageCustomRound(
                context,
                url,
                imageView,
                radius,
                color,
                placeId,
                errorId,
                strategy
            )
        }
    }

    /**
     * 加载圆角网络图片 --- 四个角都设置和带监听方法和自定义参数
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param radius         圆角角度
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * *                       占位符(Placeholders)
     * *                      图片变换(Transformations)
     * *                      缓存策略(Caching Strategies)
     * *                      编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
    override fun displayImageCustomRound(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        radius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCustomRound(
                context,
                url,
                imageView,
                radius,
                requestOptions,
                strategy,
                loaderListener
            )
        }
    }

    /**
     * 加载圆角网络图片 --- 自定义方向圆角
     *
     * @param context
     * @param url           图片地址
     * @param imageView
     * @param radius        圆角角度
     * @param leftTop       左上角是否设置radius   true： 设置 ； false： 不设置
     * @param rightTop      右上角是否设置radius  true： 设置 ； false： 不设置
     * @param leftBottom    左下角是否设置radius  true： 设置 ； false： 不设置
     * @param rightBottom   右下角是否设置radius true： 设置 ； false： 不设置
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
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
        if (client != null && context != null) {
            client!!.displayImageCustomRound(
                context,
                url,
                imageView,
                radius,
                leftTop,
                rightTop,
                leftBottom,
                rightBottom,
                strategy
            )
        }
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
        if (client != null && context != null) {
            client!!.displayImageCustomRound(
                context,
                url,
                imageView,
                radius,
                placeId,
                errorId,
                leftTop,
                rightTop,
                leftBottom,
                rightBottom,
                strategy
            )
        }
    }

    /**
     * 加载圆角网络图片 --- 自定义方向圆角和自定义参数
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param radius         圆角角度
     * @param leftTop        左上角是否设置radius   true： 设置 ； false： 不设置
     * @param rightTop       右上角是否设置radius  true： 设置 ； false： 不设置
     * @param leftBottom     左下角是否设置radius  true： 设置 ； false： 不设置
     * @param rightBottom    右下角是否设置radius true： 设置 ； false： 不设置
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
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
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCustomRound(
                context,
                url,
                imageView,
                radius,
                leftTop,
                rightTop,
                leftBottom,
                rightBottom,
                requestOptions,
                strategy
            )
        }
    }

    /**
     * 加载圆角网络图片 --- 自定义方向圆角和自定义参数,带监听方法
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param radius         圆角角度
     * @param leftTop        左上角是否设置radius   true： 设置 ； false： 不设置
     * @param rightTop       右上角是否设置radius  true： 设置 ； false： 不设置
     * @param leftBottom     左下角是否设置radius  true： 设置 ； false： 不设置
     * @param rightBottom    右下角是否设置radius true： 设置 ； false： 不设置
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
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
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImageCustomRound(
                context,
                url,
                imageView,
                radius,
                leftTop,
                rightTop,
                leftBottom,
                rightBottom,
                requestOptions,
                strategy,
                loaderListener
            )
        }
    }

    /**
     * 加载网络图片 --- 模糊处理
     *
     * @param context
     * @param url           图片地址
     * @param imageView
     * @param blurRadius    模糊数  0--25间取值
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     */
    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageBlur(context, url, imageView, blurRadius, strategy)
        }
    }

    /**
     * 加载图片----模糊处理，颜色处理
     *
     * @param context
     * @param url
     * @param imageView
     * @param blurRadius
     * @param color
     * @param strategy
     */
    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        color: Int,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageBlur(context, url, imageView, blurRadius, color, strategy)
        }
    }

    /**
     * 加载网络图片 --- 模糊处理  自定义传 placeId 和errorId
     *
     * @param context
     * @param url           图片地址
     * @param placeId       占位图id
     * @param errorId       加载图片失败时显示的图片id
     * @param imageView
     * @param blurRadius    模糊数  0--25间取值
     * @param strategy----传 null 时取默认值 DiskCacheStrategy.AUTOMATIC
     */
    override fun displayImageBlur(
        context: Context?,
        url: String?,
        placeId: Int,
        errorId: Int,
        imageView: ImageView?,
        blurRadius: Int,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageBlur(
                context,
                url,
                placeId,
                errorId,
                imageView,
                blurRadius,
                strategy
            )
        }
    }

    /**
     * 加载网络图片 --- 模糊处理带监听  自定义传 placeId 和errorId
     *
     * @param context
     * @param url            图片地址
     * @param placeId        占位图id
     * @param errorId        加载图片失败时显示的图片id
     * @param imageView
     * @param blurRadius     模糊数  0--25间取值
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
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
        if (client != null && context != null) {
            client!!.displayImageBlur(
                context,
                url,
                placeId,
                errorId,
                imageView,
                blurRadius,
                strategy,
                loaderListener
            )
        }
    }

    /**
     * 加载网络图片 --- 模糊处理 自定义参数
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param blurRadius     模糊数  0--25间取值
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     */
    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?
    ) {
        if (client != null && context != null) {
            client!!.displayImageBlur(context, url, imageView, blurRadius, requestOptions, strategy)
        }
    }

    /**
     * 加载网络图片 --- 模糊处理 自定义参数 带监听方法
     *
     * @param context
     * @param url            图片地址
     * @param imageView
     * @param blurRadius     模糊数  0--25间取值
     * @param requestOptions 很多选项设置都可以通过RequestOptions类和apply()方法完成，包括：
     * 占位符(Placeholders)
     * 图片变换(Transformations)
     * 缓存策略(Caching Strategies)
     * 编码质量、解码配置等组件选项(Component specific options)
     * @param strategy----传  null 时取默认值 DiskCacheStrategy.AUTOMATIC
     * @param loaderListener 监听回调对象
     */
    override fun displayImageBlur(
        context: Context?,
        url: String?,
        imageView: ImageView?,
        blurRadius: Int,
        requestOptions: RequestOptions?,
        strategy: DiskCacheStrategy?,
        loaderListener: IImageLoaderListener?
    ) {
        if (client != null && context != null) {
            client!!.displayImageBlur(
                context,
                url,
                imageView,
                blurRadius,
                requestOptions,
                strategy,
                loaderListener
            )
        }
    }

}