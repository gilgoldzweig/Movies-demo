package com.example.gilgoldzweig.movies.modules.network.interceptors

import android.accounts.NetworkErrorException
import com.example.gilgoldzweig.movies.extensions.Timber
import okhttp3.Interceptor
import okhttp3.Response


class InternetConnectionInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response {
        if (chain == null) throw KotlinNullPointerException("Chain is null")

        if (!isOnline()) throw NetworkErrorException("No internet connection")

        return chain.proceed(chain.request())
    }
}

fun isOnline(): Boolean {
    try {
        return Runtime.getRuntime()
                .exec("/system/bin/ping -c 1 1.1.1.1").waitFor() == 0
    } catch (e: Exception) {
        Timber.e(e) //Would be replaced with a analytics event on production
    }
    return false
}