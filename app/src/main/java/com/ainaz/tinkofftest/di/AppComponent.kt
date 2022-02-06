package com.ainaz.tinkofftest.di

import android.app.Application
import com.ainaz.tinkofftest.ui.hot.HotFragment
import com.ainaz.tinkofftest.ui.latest.LatestFragment
import com.ainaz.tinkofftest.ui.top.TopFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(latestFragment: LatestFragment)

    fun inject(topFragment: TopFragment)

    fun inject(hotFragment: HotFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun create(): AppComponent
    }
}