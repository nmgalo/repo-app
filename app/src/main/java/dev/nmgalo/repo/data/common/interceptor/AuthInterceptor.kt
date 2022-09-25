package dev.nmgalo.repo.data.common.interceptor

import dev.nmgalo.repo.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain
                .request()
                .newBuilder()
                .addHeader("Authorization", "Bearer ${BuildConfig.GITHUB_API_KEY}")
                .build()
        )
    }
}
