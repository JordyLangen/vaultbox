package com.jlangen.vaultbox.screens.vault

import com.jlangen.vaultbox.models.VaultIcon
import java.util.*

data class VaultEntry(
        val uuid: UUID,
        val title: String?,
        val username: String?,
        val password: String?,
        val url: String?,
        val notes: String?,
        val creationTime: Calendar?,
        val expires: Boolean? = false,
        val expiryTime: Calendar?,
        val lastModificationTime: Calendar?,
        val lastAccessTime: Calendar?,
        val group: String,
        val groupUuid: UUID,
        val vaultIcon: VaultIcon,
        val icon: ByteArray?)