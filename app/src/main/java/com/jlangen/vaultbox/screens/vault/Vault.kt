package com.jlangen.vaultbox.screens.vault

import java.io.Serializable

data class Vault(val name: String, val path: String, val entries: List<VaultEntry> = emptyList()) : Serializable