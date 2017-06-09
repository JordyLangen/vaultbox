package com.jlangen.vaultbox.vaults

import com.jlangen.vaultbox.vaults.vault.VaultEntry
import java.io.Serializable

data class Vault(val name: String, val path: String, val entries: List<VaultEntry> = emptyList()) : Serializable