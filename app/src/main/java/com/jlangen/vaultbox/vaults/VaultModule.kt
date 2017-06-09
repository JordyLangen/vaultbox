package com.jlangen.vaultbox.vaults

import android.content.Context
import com.jlangen.vaultbox.permissions.PermissionService
import com.jlangen.vaultbox.vaults.VaultRepository
import com.jlangen.vaultbox.vaults.vault.VaultViewCoordinator
import com.jlangen.vaultbox.vaults.VaultService
import com.jlangen.vaultbox.vaults.VaultsViewCoordinator
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class VaultModule {

    @Provides
    fun provideVaultRepository(): VaultRepository {
        return VaultRepository()
    }

    @Provides
    fun provideVaultService(vaultRepository: VaultRepository, permissionService: PermissionService, context: Context): VaultService {
        return VaultService(vaultRepository, permissionService, context)
    }

    @Provides
    fun provideVaultsViewCoordinator(vaultService: VaultService): VaultsViewCoordinator {
        return VaultsViewCoordinator(vaultService)
    }

    @Provides
    fun provideVaultViewCoordinator(vaultService: VaultService): VaultViewCoordinator {
        return VaultViewCoordinator(vaultService)
    }
}