package com.jlangen.vaultbox.vaults

import com.jlangen.vaultbox.vault.Vault

data class VaultsViewState(val isLoading: Boolean, val vaults: List<Vault>)