package com.xiangqian.hnhjt.basemodule.retrofit

import com.xiangqian.hnhjt.basemodule.util.CommonKey
import com.xiangqian.hnhjt.basemodule.util.CommonUtil
import com.xiangqian.hnhjt.basemodule.util.CommonValue
import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class CommonHeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()

        val oldUrl = originalRequest.url()

        val urlnameList = originalRequest.headers(CommonKey.baseurl_head_tag_key)

        var baseURL: HttpUrl? = if (urlnameList != null && urlnameList.size > 0) {
            val urlname = urlnameList[0]
            //根据头信息中配置的value,来匹配新的base_url地址
            //此处可以根据实际需求 进行 baseURL的切换
            if (CommonKey.super_detail_baseurl_head_tag == urlname) {
                HttpUrl.parse(CommonValue.DETAIL_STORE_BASE_URL)
            } else {
                HttpUrl.parse(CommonValue.MAIN_STORE_BASE_URL)
            }
        } else {
            HttpUrl.parse(CommonValue.MAIN_STORE_BASE_URL)
        }

        //重建新的HttpUrl，需要重新设置的url部分

        //重建新的HttpUrl，需要重新设置的url部分
        val newHttpUrl = oldUrl.newBuilder()
            .scheme(baseURL!!.scheme()) //http协议如：http或者https
            .host(baseURL!!.host()) //主机地址
            .port(baseURL!!.port()) //端口
            .build()

        //获取处理后的新newRequest

        //获取处理后的新newRequest
        val newRequest: Request = originalRequest.newBuilder()
            .addHeader("token", CommonUtil.INSTANCE.getSuperToken())
            .addHeader("version", CommonUtil.INSTANCE.getAppVersionCode())
            .header("Content-Type", "application/json;charset=utf-8")
            .header("Accept", "application/json")
            .url(newHttpUrl)
            .build()

        return chain.proceed(newRequest)

    }
}