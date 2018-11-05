package com.example.gilgoldzweig.movies.application

import android.app.Application
import android.net.Network
import com.example.gilgoldzweig.movies.BuildConfig
import com.example.gilgoldzweig.movies.extensions.Timber
import com.example.gilgoldzweig.movies.modules.db.DatabaseModule
import com.example.gilgoldzweig.movies.modules.di.*
import com.example.gilgoldzweig.movies.modules.network.NetworkModule
import com.example.gilgoldzweig.movies.modules.vm.SelectedMovieViewModelFactory
import com.github.anrwatchdog.ANRWatchDog
import com.squareup.leakcanary.LeakCanary
import goldzweigapps.com.reactive.runSafeOnIO
import io.reactivex.Completable

class MoviesApplication : Application() {

    companion object {
        lateinit var networkComponent: NetworkComponent
        lateinit var databaseComponent: DatabaseComponent
        lateinit var movieInformationComponent: MovieInformationComponent
    }

    private lateinit var databaseModule: DatabaseModule
    private lateinit var networkModule: NetworkModule

    override fun onCreate() {
        super.onCreate()
//        if (LeakCanary.isInAnalyzerProcess(this)) return //Comment-out because apk is in debug

//        LeakCanary.install(this) //Comment-out because apk is in debug


        if (BuildConfig.DEBUG) {

            Timber.plant(Timber.DebugTree())
        }

        ANRWatchDog()
            .setIgnoreDebugger(true)
            .setANRListener {
                Timber.e(it)
            }
            .setInterruptionListener {
                Timber.e(it)
            }
            .start()

        databaseModule = DatabaseModule(this)
        networkModule = NetworkModule()

        networkComponent = DaggerNetworkComponent
            .builder()
            .networkModule(networkModule)
            .build()

        databaseComponent = DaggerDatabaseComponent
            .builder()
            .databaseModule(databaseModule)
            .build()

        movieInformationComponent = DaggerMovieInformationComponent.builder()
            .databaseModule(databaseModule)
            .networkModule(networkModule)
            .build()


    }
}