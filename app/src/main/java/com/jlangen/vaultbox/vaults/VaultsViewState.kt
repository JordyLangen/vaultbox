package com.jlangen.vaultbox.vaults

data class VaultsViewState(
        var isLoading: Boolean,
        var vaults: List<Vault>)