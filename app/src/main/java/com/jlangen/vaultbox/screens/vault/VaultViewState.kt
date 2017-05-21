package com.jlangen.vaultbox.screens.vault

import com.jlangen.vaultbox.models.Vault

data class VaultViewState(
        val vault: Vault,
        val unlockState: UnlockState) {

    enum class UnlockState {
        Closed,
        Opened,
        InvalidPasswordUnlockAttempt
    }
}