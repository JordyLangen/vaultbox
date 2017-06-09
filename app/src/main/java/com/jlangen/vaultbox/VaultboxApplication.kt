package com.jlangen.vaultbox

import android.app.Application
import com.jlangen.vaultbox.architecture.AppModule
import com.jlangen.vaultbox.architecture.DaggerVaultboxComponent
import com.jlangen.vaultbox.architecture.Navigator
import com.jlangen.vaultbox.architecture.VaultboxComponent
import com.jlangen.vaultbox.permissions.PermissionModule
import com.jlangen.vaultbox.vaults.VaultModule

class VaultboxApplication : Application() {

    companion object {
        lateinit var component: VaultboxComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerVaultboxComponent.builder()
                .appModule(AppModule(this))
                .permissionModule(PermissionModule())
                .vaultModule(VaultModule())
                .build()
    }
}