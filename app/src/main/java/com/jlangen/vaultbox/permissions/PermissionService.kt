package com.jlangen.vaultbox.permissions

import android.content.Context
import android.content.pm.PackageManager
import android.support.v4.content.ContextCompat
import io.reactivex.Observable
import com.jakewharton.rxrelay2.PublishRelay


class PermissionService(private val context: Context) {

    private val requestRelay = PublishRelay.create<String>()
    private val grantRelay = PublishRelay.create<String>()

    fun observeRequests(): Observable<String> {
        return requestRelay
    }

    fun request(permission: String): PublishRelay<String> {
        requestRelay.accept(permission)
        return grantRelay
    }

    fun granted(permission: String) {
        grantRelay.accept(permission)
    }

    fun has(permission: String) : Boolean {
        return ContextCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED
    }
}