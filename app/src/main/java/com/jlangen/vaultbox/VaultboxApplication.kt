package com.jlangen.vaultbox

import android.app.Application
import com.jlangen.vaultbox.architecture.AppModule
import com.jlangen.vaultbox.architecture.DaggerVaultboxComponent
import com.jlangen.vaultbox.architecture.VaultboxComponent
import com.jlangen.vaultbox.database.DatabaseModule

class VaultboxApplication : Application() {

    companion object {
        lateinit var component: VaultboxComponent
    }

    override fun onCreate() {
        super.onCreate()
        System.loadLibrary("final-key")

        component = DaggerVaultboxComponent.builder()
                .appModule(AppModule(this))
                .databaseModule(DatabaseModule())
                .build()
    }
}