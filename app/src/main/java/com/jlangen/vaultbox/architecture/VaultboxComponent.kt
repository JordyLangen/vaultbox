package com.jlangen.vaultbox.architecture

import com.jlangen.vaultbox.vaults.VaultsModule
import com.jlangen.vaultbox.vaults.VaultOverviewActivity
import com.jlangen.vaultbox.vaults.VaultsViewCoordinator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, VaultsModule::class))
interface VaultboxComponent {

    fun inject(vaultOverviewActivity: VaultOverviewActivity)

    fun resolveVaultsViewCoordinator(): VaultsViewCoordinator
}