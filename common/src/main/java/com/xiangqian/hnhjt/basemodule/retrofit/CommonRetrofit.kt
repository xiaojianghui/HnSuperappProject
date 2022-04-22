package com.xiangqian.hnhjt.basemodule.retrofit

import com.ihsanbal.logging.Level
import com.ihsanbal.logging.LoggingInterceptor
import com.xiangqian.hnhjt.basemodule.util.CommonValue
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class CommonRetrofit {
    private var retrofit: Retrofit? = null

    /**
     * 类级的内部类，也就是静态的成员式内部类，该内部类的实例与外部类的实例
     * 没有绑定关系，而且只有被调用到才会装载，从而实现了延迟加载
     */
    private object SingletonHolder {
        /**
         * 静态初始化器，由JVM来保证线程安全
         */
        val INSTANCE = CommonRetrofit()
    }


    fun getInstance(): CommonRetrofit? {
        return SingletonHolder.INSTANCE
    }

    private fun CommonRetrofit() {
        //  File httpCacheDirectory = new File("/sdcard", "cache_tiens");
        // Cache cache = new Cache(httpCacheDirectory, 1024 * 1024 * 10); //10M

        //  File httpCacheDirectory = new File("/sdcard", "cache_tiens");
        // Cache cache = new Cache(httpCacheDirectory, 1024 * 1024 * 10); //10M
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS) // .cache(cache)
            .addInterceptor(CommonHeaderInterceptor())
            .addInterceptor(
                LoggingInterceptor.Builder()
                    .loggable(true)
                    .setLevel(Level.BASIC)
                    .log(Platform.INFO)
                    .request("Request")
                    .response("Response").build()
            )
        retrofit = Retrofit.Builder()
            .client(builder.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(CommonValue.MAIN_STORE_BASE_URL)
            .build()
    }


    fun getRetrofit(): Retrofit? {
        return retrofit
    }

}