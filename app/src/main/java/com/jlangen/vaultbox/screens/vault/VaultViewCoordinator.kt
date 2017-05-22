package com.jlangen.vaultbox.screens.vault

import android.util.Log
import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import com.jlangen.vaultbox.services.VaultService
import com.jlangen.vaultbox.screens.vault.VaultViewState.UnlockState
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class VaultViewCoordinator(private val vaultService: VaultService) : Coordinator<VaultViewState, VaultView>() {

    override var state: VaultViewState = VaultViewState(VaultService.SharedState.selectedVault ?: throw IllegalStateException("tried to show the vault view without a selected view"))
    private val disposables = CompositeDisposable()

    override fun attach(view: VaultView) {
        view.render(state)

        disposables += view.openVaultIntents
                .doOnNext {
                    state = state.copy(unlockState = UnlockState.Unlocking)
                    view.render(state)
                }
                .observeOn(Schedulers.io())
                .switchMap { password ->
                    vaultService.open(state.vault, password)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ resultOrError ->
                    if (resultOrError.result != null) {
                        state = VaultViewState(resultOrError.result, resultOrError.result.entries, UnlockState.Opened)
                    } else {
                        state = state.copy(unlockState = UnlockState.InvalidPasswordUnlockAttempt)
                    }
                    view.render(state)
                })

        disposables += view.searchIntents
                .observeOn(Schedulers.io())
                .debounce(300, TimeUnit.MILLISECONDS)
                .map { text ->
                    vaultService.searchVault(state.vault, text)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { filteredEntries ->
                    state = state.copy(filteredEntries = filteredEntries)
                    view.render(state)
                }
    }

    override fun detach(view: VaultView) {
        disposables.dispose()
    }
}