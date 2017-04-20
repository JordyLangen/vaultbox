package com.jlangen.vaultbox.vaults

import com.jlangen.vaultbox.permissions.PermissionService
import dagger.Module
import dagger.Provides

@Module
class VaultsModule {

    @Provides
    fun provideVaultRepository() : VaultRepository {
        return VaultRepository()
    }

    @Provides
    fun provideVaultService(vaultRepository: VaultRepository, permissionService: PermissionService) : VaultService {
        return VaultService(vaultRepository, permissionService)
    }

    @Provides
    fun provideVaultsViewCoordinator(vaultService: VaultService): VaultsViewCoordinator {
        return VaultsViewCoordinator(vaultService)
    }
}