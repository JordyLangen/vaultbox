package com.jlangen.vaultbox.screens.vault

import com.jlangen.vaultbox.models.VaultIcon
import de.slackspace.openkeepass.domain.Entry
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
        val icon: ByteArray?) {

    constructor(entry: Entry, group: String, groupUuid: UUID, vaultIcon: VaultIcon, icon: ByteArray?) : this(
            entry.uuid,
            entry.title,
            entry.username,
            entry.password,
            entry.url,
            entry.notes,
            entry.times.creationTime,
            entry.times.expires(),
            entry.times.expiryTime,
            entry.times.lastModificationTime,
            entry.times.lastAccessTime, group, groupUuid, vaultIcon, icon)
}