package com.jlangen.vaultbox.screens.vaults

import com.jlangen.vaultbox.models.Vault

data class VaultsViewState(val isLoading: Boolean, val vaults: List<Vault>)