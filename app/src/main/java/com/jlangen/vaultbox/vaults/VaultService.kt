package com.jlangen.vaultbox.vaults

import android.Manifest
import com.jlangen.vaultbox.permissions.PermissionService
import io.reactivex.Observable

class VaultService(private val vaultRepository: VaultRepository, private val permissionService: PermissionService) {

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
}