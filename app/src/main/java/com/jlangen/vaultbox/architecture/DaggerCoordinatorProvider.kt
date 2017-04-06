package com.jlangen.vaultbox.architecture

import android.view.View
import com.jlangen.vaultbox.VaultboxApplication
import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import com.jlangen.vaultbox.architecture.coordinators.CoordinatorProvider
import com.jlangen.vaultbox.database.DatabasesView

class DaggerCoordinatorProvider : CoordinatorProvider {

    override fun provideCoordinator(view: View): Coordinator<*, *>? {
        if (view is DatabasesView) {
            return VaultboxApplication.component.resolveDatabasesCoordinator()
        }

        return null
    }
}