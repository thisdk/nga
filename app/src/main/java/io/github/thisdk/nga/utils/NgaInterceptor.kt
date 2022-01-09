package io.github.thisdk.nga.utils

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import java.lang.reflect.Field


class NgaInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("User-Agent", "Nga_Official/80030([XiaoMi MIX3];Android11)")
            .build()
        val response = chain.proceed(request)
        val responseBody = response.body
        val contentType = response.header("Content-Type")
        val headers = response.headers.newBuilder()
            .removeAll("Content-Type")
            .add("Content-Type", "$contentType; charset=gbk;")
            .build()
        try {
            val responseField: Field = response::class.java.getDeclaredField("headers")
            responseField.isAccessible = true
            responseField.set(response, headers)
            if (responseBody != null) {
                val responseBodyField: Field =
                    responseBody::class.java.getDeclaredField("contentTypeString")
                responseBodyField.isAccessible = true
                responseBodyField[responseBody] = "$contentType; charset=gbk;"
            }
        } catch (e: NoSuchFieldException) {
            throw IOException("use reflect to setting header occurred an error", e)
        } catch (e: IllegalAccessException) {
            throw IOException("use reflect to setting header occurred an error", e)
        }
        return response
    }

}