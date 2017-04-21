package com.jlangen.vaultbox.architecture

import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import com.jlangen.vaultbox.VaultboxApplication
import com.jlangen.vaultbox.permissions.PermissionService
import io.reactivex.disposables.Disposable

open class BaseActivity : AppCompatActivity() {

    companion object {
        const val REQUEST_CODE_PERMISSIONS = 1024
    }

    private var permissionService: PermissionService = VaultboxApplication.component.resolvePermissionService()
    private var navigator: Navigator = VaultboxApplication.component.resolveNavigator()

    private var permissionRequestsDisposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observePermissionRequests()
    }

    override fun onResume() {
        super.onResume()
        navigator.navigated(this)
        if (permissionRequestsDisposable == null) {
            observePermissionRequests()
        }
    }

    private fun observePermissionRequests() {
        permissionRequestsDisposable = permissionService.observeRequests()
                .subscribe { permission ->
                    ActivityCompat.requestPermissions(this, arrayOf(permission), REQUEST_CODE_PERMISSIONS)
                }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode != REQUEST_CODE_PERMISSIONS) {
            return
        }

        permissions.forEachIndexed { index, permission ->
            if (grantResults[index] == PackageManager.PERMISSION_GRANTED) {
                permissionService.granted(permission)
            } else {
                permissionService.request(permission)
            }
        }
    }

    override fun onPause() {
        super.onPause()
        navigator.disable()
        permissionRequestsDisposable?.dispose()
        permissionRequestsDisposable = null
    }
}