package com.jlangen.vaultbox.architecture.coordinators

import android.view.View
import android.view.ViewGroup

internal class Binder(private val provider: CoordinatorProvider) : ViewGroup.OnHierarchyChangeListener {

    override fun onChildViewAdded(parent: View, child: View) {
        Coordinators.bind(child, provider)
    }

    override fun onChildViewRemoved(parent: View, child: View) {}
}