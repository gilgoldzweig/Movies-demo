package com.example.gilgoldzweig.movies.base

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

interface BasePresenter<T: BasePresenterCallBack>: LifecycleObserver {
    var callback: T

    fun observe() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun onStart() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun onStop() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    fun onResume() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    fun onPause() = Unit

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() = Unit
}