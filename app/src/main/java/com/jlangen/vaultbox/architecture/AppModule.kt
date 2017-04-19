package com.jlangen.vaultbox.architecture

import android.content.Context
import com.jlangen.vaultbox.architecture.Navigator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val context: Context) {

    @Provides
    fun provideContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideNavigator(): Navigator {
        return Navigator(context)
    }
}