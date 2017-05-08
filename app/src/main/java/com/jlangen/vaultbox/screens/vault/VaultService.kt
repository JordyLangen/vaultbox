package com.jlangen.vaultbox.screens.vault

import android.Manifest
import android.content.Context
import android.content.Intent
import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jlangen.vaultbox.screens.vault.VaultActivity
import com.jlangen.vaultbox.permissions.PermissionService
import com.jlangen.vaultbox.screens.vault.Vault
import de.slackspace.openkeepass.KeePassDatabase
import io.reactivex.Observable

class VaultService(private val vaultRepository: VaultRepository,
                   private val permissionService: PermissionService,
                   private val context: Context) {

    var selectedVault: Vault? = null

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

    fun open(vault: Vault) {
        selectedVault = vault
        context.startActivity(Intent(context, VaultActivity::class.java))
    }

    fun decrypt(vault: Vault) {
        val result = KeePassDatabase.getInstance(vault.path).openDatabase("test123")
        Log.d("test", result.toString())
    }
}