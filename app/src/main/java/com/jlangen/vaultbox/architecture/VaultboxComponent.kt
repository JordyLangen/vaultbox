package com.jlangen.vaultbox.architecture

import com.jlangen.vaultbox.permissions.PermissionModule
import com.jlangen.vaultbox.permissions.PermissionService
import com.jlangen.vaultbox.vaults.VaultModule
import com.jlangen.vaultbox.vaults.vault.VaultViewCoordinator
import com.jlangen.vaultbox.vaults.VaultOverviewActivity
import com.jlangen.vaultbox.vaults.VaultsViewCoordinator
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