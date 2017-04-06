package com.jlangen.vaultbox.architecture.coordinators

import android.view.View
import com.jlangen.vaultbox.R

internal class Binding<TState, TView : ViewRenderer<TState>>(private val coordinator: Coordinator<TState, TView>, private val view: View) : View.OnAttachStateChangeListener {
    private var attached: View? = null

    override fun onViewAttachedToWindow(attachedView: View) {
        if (attachedView !== attached) {
            attached = attachedView
            if (coordinator.isAttached) {
                throw IllegalStateException("Coordinator $coordinator is already attached to a View")
            }
            coordinator.isAttached = true
            coordinator.attach(view as TView)
            view.setTag(R.id.coordinator, coordinator)
        }
    }

    override fun onViewDetachedFromWindow(v: View) {
        if (v === attached) {
            attached = null
            coordinator.detach(view as TView)
            coordinator.isAttached = false
            view.setTag(R.id.coordinator, null)
        }
    }
}