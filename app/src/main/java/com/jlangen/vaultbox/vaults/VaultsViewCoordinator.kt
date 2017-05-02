package com.jlangen.vaultbox.vaults

import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VaultsViewCoordinator(var vaultService: VaultService) : Coordinator<VaultsViewState, VaultsView>() {

    private val viewState: VaultsViewState = VaultsViewState(true, emptyList())

    override fun attach(view: VaultsView) {
        view.render(viewState)

        vaultService.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { vaults ->
                    view.render(VaultsViewState(false, vaults))
                }
    }
}