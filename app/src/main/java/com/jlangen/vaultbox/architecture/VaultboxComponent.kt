package com.jlangen.vaultbox.architecture

import com.jlangen.vaultbox.permissions.PermissionModule
import com.jlangen.vaultbox.permissions.PermissionService
import com.jlangen.vaultbox.vaults.VaultsModule
import com.jlangen.vaultbox.vaults.VaultOverviewActivity
import com.jlangen.vaultbox.vaults.VaultsViewCoordinator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, PermissionModule::class, VaultsModule::class))
interface VaultboxComponent {

    fun resolveVaultsViewCoordinator(): VaultsViewCoordinator

    fun resolvePermissionService(): PermissionService

    fun resolveNavigator(): Navigator
}