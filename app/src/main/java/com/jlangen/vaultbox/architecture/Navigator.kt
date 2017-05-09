package com.jlangen.vaultbox.architecture

import android.app.Activity
import android.content.Context
import android.content.Intent
import kotlin.reflect.KClass

class Navigator(private val context: Context) {

    private var currentActivity: Activity? = null

    fun navigated(currentActivity: Activity) {
        this.currentActivity = currentActivity
    }

    fun disable() {
        currentActivity = null
    }

    fun goTo(nextActivity: KClass<*>) {
        val intent = Intent(context, nextActivity.java)
        context.startActivity(intent)
    }

    fun close() {
        currentActivity?.finish()
    }
}