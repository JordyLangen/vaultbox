package com.jlangen.vaultbox.architecture

import com.jlangen.vaultbox.permissions.PermissionModule
import com.jlangen.vaultbox.permissions.PermissionService
import com.jlangen.vaultbox.screens.vault.VaultModule
import com.jlangen.vaultbox.screens.vault.VaultViewCoordinator
import com.jlangen.vaultbox.screens.vaults.VaultOverviewActivity
import com.jlangen.vaultbox.screens.vaults.VaultsViewCoordinator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, PermissionModule::class, VaultModule::class))
interface VaultboxComponent {

    fun resolveVaultsViewCoordinator(): VaultsViewCoordinator

    fun resolveVaultViewCoordinator(): VaultViewCoordinator

    fun resolvePermissionService(): PermissionService

    fun resolveNavigator(): Navigator
}