package com.jlangen.vaultbox.vaults

import com.jlangen.vaultbox.architecture.coordinators.Coordinator
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class VaultsViewCoordinator(var vaultRepository: VaultRepository) : Coordinator<VaultsViewState, VaultsState>() {

    private val viewState: VaultsViewState = VaultsViewState(true, emptyList())

    override fun attach(view: VaultsState) {
        view.render(viewState)

        vaultRepository.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    // todo: rx these properties and eliminate render calls?
                    viewState.isLoading = false
                    viewState.vaults = it
                    view.render(viewState)
                }
    }
}