package com.jlangen.vaultbox.architecture

import com.jlangen.vaultbox.database.DatabaseModule
import com.jlangen.vaultbox.database.DatabasesActivity
import com.jlangen.vaultbox.database.DatabasesCoordinator
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, DatabaseModule::class))
interface VaultboxComponent {

    fun inject(databasesActivity: DatabasesActivity)

    fun resolveDatabasesCoordinator(): DatabasesCoordinator
}