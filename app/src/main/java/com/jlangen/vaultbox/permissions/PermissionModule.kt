package com.jlangen.vaultbox.permissions

import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PermissionModule {

    @Provides
    @Singleton
    fun providePermissionService(context: Context): PermissionService {
        return PermissionService(context)
    }
}