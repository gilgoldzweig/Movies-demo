package com.example.gilgoldzweig.movies.base

import android.arch.lifecycle.Lifecycle

interface BasePresenterCallBack {
    fun getLifecycle(): Lifecycle

    val name: String?
    get() {
        return this::class.qualifiedName ?: "No name"
    }

    fun onBackPress() = Unit
}
