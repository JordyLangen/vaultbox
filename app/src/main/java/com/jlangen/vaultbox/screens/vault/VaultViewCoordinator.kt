package com.jlangen.vaultbox.screens.vault

import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import com.jlangen.vaultbox.services.VaultService
import com.jlangen.vaultbox.screens.vault.VaultViewState.UnlockState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class VaultViewCoordinator(private val vaultService: VaultService) : Coordinator<VaultViewState, VaultView>() {

    override var state: VaultViewState = VaultViewState(vaultService.selectedVault ?: throw IllegalStateException("tried to show the vault view without a selected view"), UnlockState.Closed)
    private val disposables = CompositeDisposable()

    override fun attach(view: VaultView) {
        view.render(state)

        disposables += view.openVaultIntents
                .subscribeOn(Schedulers.io())
                .switchMap { password ->
                    vaultService.open(state.vault, password)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultOrError ->
                    if (resultOrError.result != null) {
                        state = VaultViewState(resultOrError.result, UnlockState.Opened)
                    } else {
                        state = VaultViewState(state.vault, UnlockState.InvalidPasswordUnlockAttempt)
                    }
                    view.render(state)
                })
    }

    override fun detach(view: VaultView) {
        disposables.dispose()
    }
}