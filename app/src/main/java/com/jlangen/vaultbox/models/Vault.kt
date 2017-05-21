package com.jlangen.vaultbox.models

import com.jlangen.vaultbox.screens.vault.VaultEntry
import java.io.Serializable

data class Vault(val name: String, val path: String, val entries: List<VaultEntry> = emptyList()) : Serializable