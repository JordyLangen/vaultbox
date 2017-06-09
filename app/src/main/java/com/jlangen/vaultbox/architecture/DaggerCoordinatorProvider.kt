package com.jlangen.vaultbox.architecture

import android.view.View
import com.jlangen.vaultbox.VaultboxApplication
import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import com.jlangen.vaultbox.architecture.coordinators.CoordinatorProvider
import com.jlangen.vaultbox.vaults.vault.VaultView
import com.jlangen.vaultbox.vaults.VaultsView

class DaggerCoordinatorProvider : CoordinatorProvider {

    override fun provideCoordinator(view: View): Coordinator<*, *>? {
        if (view is VaultsView) {
            return VaultboxApplication.component.resolveVaultsViewCoordinator()
        }

        if (view is VaultView) {
            return VaultboxApplication.component.resolveVaultViewCoordinator()
        }

        return null
    }
}