package com.jlangen.vaultbox.vaults

import dagger.Module
import dagger.Provides

@Module
class VaultsModule {

    @Provides
    fun provideVaultRepository() : VaultRepository {
        return VaultRepository()
    }

    @Provides
    fun provideVaultsViewCoordinator(databaseRepository: VaultRepository): VaultsViewCoordinator {
        return VaultsViewCoordinator(databaseRepository)
    }
}