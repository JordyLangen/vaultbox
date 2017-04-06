package com.jlangen.vaultbox.database

import com.jlangen.vaultbox.architecture.coordinators.Coordinator

class DatabasesCoordinator(var databaseRepository: KeepassDatabaseRepository) : Coordinator<DatabasesViewState, DatabasesView>() {

    override fun attach(view: DatabasesView) {

    }
}