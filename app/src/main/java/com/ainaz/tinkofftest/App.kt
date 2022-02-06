package com.ainaz.tinkofftest

import android.app.Application
import android.content.Context
import com.ainaz.tinkofftest.di.AppComponent
import com.ainaz.tinkofftest.di.DaggerAppComponent

class App : Application() {
    private var _appComponent: AppComponent? = null

    val appComponent: AppComponent
        get() = checkNotNull(_appComponent)

    override fun onCreate() {
        super.onCreate()
        _appComponent = DaggerAppComponent.builder()
            .application(this)
            .create()
    }
}