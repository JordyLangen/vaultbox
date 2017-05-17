package com.jlangen.vaultbox.screens.vault

data class VaultViewState(
        val vault: Vault,
        val unlockState: UnlockState) {

    enum class UnlockState {
        Closed,
        Opened,
        InvalidPasswordUnlockAttempt
    }
}