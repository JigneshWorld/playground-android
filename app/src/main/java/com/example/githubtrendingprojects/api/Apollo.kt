package com.example.githubtrendingprojects.api

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.githubtrendingprojects.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response

private class AuthorizationInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.API_ACCESS_TOKEN}")
            .build()
        return chain.proceed(request)
    }
}

fun apolloClient(): ApolloClient {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthorizationInterceptor())
        .build()

    return ApolloClient.Builder()
        .serverUrl(BuildConfig.API_SERVER_URL)
        .okHttpClient(okHttpClient)
        .build()
}

