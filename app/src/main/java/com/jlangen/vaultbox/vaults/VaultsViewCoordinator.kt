package com.jlangen.vaultbox.vaults

import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import com.jlangen.vaultbox.vault.VaultService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.schedulers.Schedulers

class VaultsViewCoordinator(var vaultService: VaultService) : Coordinator<VaultsViewState, VaultsView>() {

    override var state: VaultsViewState = VaultsViewState(true, emptyList())
    private val disposables = CompositeDisposable()

    override fun attach(view: VaultsView) {
        view.render(state)

        disposables += view.openVaultIntents
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { vaultService.open(it) }

        disposables += vaultService.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { vaults ->
                    state = VaultsViewState(false, vaults)
                    view.render(state)
                }
    }

    override fun detach(view: VaultsView) {
        disposables.dispose()
    }
}