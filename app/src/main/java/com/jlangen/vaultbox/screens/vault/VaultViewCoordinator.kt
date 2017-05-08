package com.jlangen.vaultbox.screens.vault

import android.util.Log
import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import com.jlangen.vaultbox.services.VaultService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VaultViewCoordinator(private val vaultService: VaultService) : Coordinator<VaultViewState, VaultView>() {

    override var state: VaultViewState = VaultViewState(vaultService.selectedVault ?: throw IllegalStateException("tried to show the vault view without a selected view"), false, false)

    override fun attach(view: VaultView) {
        Log.d("VaultView", "current state is $state")

        view.openVaultIntents
                .subscribeOn(Schedulers.io())
                .switchMap { password ->
                    vaultService.open(state.vault, password)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ vault ->
                    state = VaultViewState(vault, true, false)
                }, {
                    state = VaultViewState(state.vault, false, true)
                })

    }
}