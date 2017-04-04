package com.jlangen.vaultbox

import android.app.Application

class VaultboxApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        System.loadLibrary("final-key")
    }
}