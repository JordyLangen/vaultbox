package com.jlangen.vaultbox.vaults

import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VaultsViewCoordinator(var vaultService: VaultService) : Coordinator<VaultsViewState, VaultsView>() {

    override var state: VaultsViewState = VaultsViewState(true, emptyList())

    override fun attach(view: VaultsView) {
        view.render(state)

        if (state.vaults.isNotEmpty()) {
            return
        }

        vaultService.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { vaults ->
                    state = VaultsViewState(false, vaults)
                    view.render(state)
                }
    }
}