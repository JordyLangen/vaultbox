package com.jlangen.vaultbox.screens.vault

import android.util.Log
import com.jlangen.vaultbox.architecture.coordinators.Coordinator

class VaultViewCoordinator(private val vaultService: VaultService) : Coordinator<VaultViewState, VaultView>() {

    override var state: VaultViewState = VaultViewState(vaultService.selectedVault ?: throw IllegalStateException("tried to open the vault view without a selected view"))

    override fun attach(view: VaultView) {
        Log.d("VaultView", "current state is $state")
    }
}