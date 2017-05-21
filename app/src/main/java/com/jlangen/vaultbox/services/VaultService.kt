package com.jlangen.vaultbox.services

import android.Manifest
import android.content.Context
import android.content.Intent
import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.screens.vault.VaultActivity
import com.jlangen.vaultbox.permissions.PermissionService
import com.jlangen.vaultbox.screens.vault.Vault
import com.jlangen.vaultbox.repositories.VaultRepository
import com.jlangen.vaultbox.screens.vault.VaultEntry
import de.slackspace.openkeepass.KeePassDatabase
import de.slackspace.openkeepass.exception.KeePassDatabaseUnreadableException
import io.reactivex.Observable

class VaultService(private val vaultRepository: VaultRepository,
                   private val permissionService: PermissionService,
                   private val context: Context) {

    object SharedState {
        var selectedVault: Vault? = null
    }

    fun findAll(): Observable<List<Vault>> {
        if (permissionService.has(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            return vaultRepository.findAll()
        } else {
            return requestRequiredPermissions()
                    .flatMap { isGranted ->
                        if (isGranted) vaultRepository.findAll() else Observable.empty()
                    }
        }
    }

    private fun requestRequiredPermissions(): Observable<Boolean> {
        return permissionService.request(Manifest.permission.READ_EXTERNAL_STORAGE)
                .flatMap { grantedPermission ->
                    if (grantedPermission == Manifest.permission.READ_EXTERNAL_STORAGE) {
                        Observable.just(true)
                    } else {
                        requestRequiredPermissions()
                    }
                }
    }

    fun show(vault: Vault) {
        SharedState.selectedVault = vault
        context.startActivity(Intent(context, VaultActivity::class.java))
    }

    fun open(vault: Vault, password: String): Observable<ResultOrError<Vault>> {
        if (password.isBlank()) {
            return Observable.just(ResultOrError(exception = KeePassDatabaseUnreadableException(context.getString(R.string.vault_unlock_error_invalid_password))))
        }

        return Observable.fromCallable {
            try {
                val database = KeePassDatabase.getInstance(vault.path).openDatabase(password)
                val entries = database.entries.map { entry ->
                    VaultEntry(entry.uuid, entry.title, entry.username, entry.password, entry.url, entry.notes, entry.times.creationTime, entry.times.expires(), entry.times.expiryTime, entry.times.lastModificationTime)
                }

                ResultOrError(result = Vault(vault.name, vault.path, entries))
            } catch (exception: KeePassDatabaseUnreadableException) {
                ResultOrError<Vault>(exception = exception)
            }
        }
    }
}