package com.jlangen.vaultbox.screens.vault

data class VaultViewState(
        val vault: Vault,
        val isOpened: Boolean,
        val isInvalidPassword: Boolean)