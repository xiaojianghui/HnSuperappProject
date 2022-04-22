package com.xiangqian.hnhjt.basemodule.glide.tranform

import android.graphics.Bitmap
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import com.bumptech.glide.load.resource.bitmap.TransformationUtils
import com.bumptech.glide.util.Util
import java.nio.ByteBuffer
import java.security.MessageDigest

class RoundRadiusTransform(roundingRadius: Int) : BitmapTransformation() {
    //  此处用实际类的完整路径
    private val ID = "basic.common.glide.tranform.RoundRadiusTransform"
    private val ID_BYTES = ID.toByteArray(CHARSET)

    private var roundingRadius = 0

    private var isLeftTop: Boolean = false
    private var isRightTop: Boolean = false
    private var isLeftBottom: Boolean = false
    private var isRightBottom: Boolean = false


    init {
        this.roundingRadius = roundingRadius
    }

    /**
     * 需要设置圆角的部分
     *
     * @param leftTop     左上角
     * @param rightTop    右上角
     * @param leftBottom  左下角
     * @param rightBottom 右下角
     */
    fun setNeedCorner(
        leftTop: Boolean,
        rightTop: Boolean,
        leftBottom: Boolean,
        rightBottom: Boolean
    ) {
        isLeftTop = leftTop
        isRightTop = rightTop
        isLeftBottom = leftBottom
        isRightBottom = rightBottom
    }

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap? {
        return TransformationUtils.roundedCorners(
            pool, toTransform,
            (if (isLeftTop) roundingRadius else 0.toFloat()) as Float,
            (if (isRightTop) roundingRadius else 0.toFloat()) as Float,
            (if (isRightBottom) roundingRadius else 0.toFloat()) as Float,
            (if (isLeftBottom) roundingRadius else 0.toFloat()) as Float
        )
    }


    override fun equals(o: Any?): Boolean {
        if (o is RoundRadiusTransform) {
            return roundingRadius == o.roundingRadius
        }
        return false
    }

    override fun hashCode(): Int {
        return Util.hashCode(ID.hashCode(), Util.hashCode(roundingRadius))
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
        messageDigest.update(ID_BYTES)
        val radiusData = ByteBuffer.allocate(4).putInt(roundingRadius).array()
        messageDigest.update(radiusData)
    }
}