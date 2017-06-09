package com.jlangen.vaultbox.vaults.vault

import com.jlangen.vaultbox.vaults.Vault

data class VaultViewState(
        val vault: Vault,
        val filteredEntries: List<VaultEntry> = emptyList(),
        val unlockState: UnlockState = VaultViewState.UnlockState.Closed) {

    enum class UnlockState {
        Closed,
        Opened,
        Unlocking,
        InvalidPasswordUnlockAttempt
    }
}