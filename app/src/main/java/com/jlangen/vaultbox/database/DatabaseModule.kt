package com.jlangen.vaultbox.database

import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    @Provides
    fun provideKeepassDatabaseRepository() : KeepassDatabaseRepository {
        return KeepassDatabaseRepository()
    }

    @Provides
    fun provideDatabasesCoordinator(databaseRepository: KeepassDatabaseRepository): DatabasesCoordinator {
        return DatabasesCoordinator(databaseRepository)
    }
}