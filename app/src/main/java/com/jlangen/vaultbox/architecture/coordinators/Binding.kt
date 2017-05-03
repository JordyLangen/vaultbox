@file:Suppress("UNCHECKED_CAST")

package com.jlangen.vaultbox.architecture.coordinators

import android.view.View
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.architecture.state.StateRenderer
import com.jlangen.vaultbox.architecture.state.StateStore

internal class Binding<in TState, TView : StateRenderer<TState>>(private val coordinator: Coordinator<TState, TView>, private val view: View) : View.OnAttachStateChangeListener {
    private var attached: View? = null

    override fun onViewAttachedToWindow(attachedView: View) {
        if (attachedView === attached) return

        if (coordinator.isAttached) {
            throw IllegalStateException("Coordinator $coordinator is already attached to a View")
        }

        attached = attachedView

        coordinator.isAttached = true
        val state = StateStore.resolve<TState>(view.id, view.context)
        if (state != null) {
            coordinator.state = state
        }

        coordinator.attach(view as TView)
        view.setTag(R.id.coordinator, coordinator)
    }

    override fun onViewDetachedFromWindow(view: View) {
        if (view !== attached) return
        StateStore.update(view.id, coordinator.state, view.context)

        attached = null
        coordinator.detach(this.view as TView)
        coordinator.isAttached = false
        this.view.setTag(R.id.coordinator, null)
    }
}