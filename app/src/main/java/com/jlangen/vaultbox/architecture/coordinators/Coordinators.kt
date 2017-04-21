package com.jlangen.vaultbox.architecture.coordinators

import android.view.View
import android.view.ViewGroup
import com.jlangen.vaultbox.R

object Coordinators {

    /**
     * Attempts to bind a view to a [Coordinator].

     * Immediately calls provider to obtain a Coordinator for the view. If a non-null Coordinator is
     * returned, that Coordinator is permanently bound to the View.
     */
    fun bind(view: View, provider: CoordinatorProvider) {
        val coordinator = provider.provideCoordinator(view) ?: return

        val binding = Binding(coordinator as Coordinator<Nothing, Nothing>, view)
        view.addOnAttachStateChangeListener(binding)
        // Sometimes we missed the first attach because the child's already been added.
        // Sometimes we didn't. The binding keeps track to avoid double attachment of the Coordinator,
        // and to guard against attachment to two different views simultaneously.
        binding.onViewAttachedToWindow(view)
    }

    /**
     * Installs a binder that calls [.bind] for any child view added
     * to the group.
     */
    fun installBinder(viewGroup: ViewGroup, provider: CoordinatorProvider) {
        viewGroup.setOnHierarchyChangeListener(Binder(provider))
    }

    fun getCoordinator(view: View): Coordinator<*, *> {
        return view.getTag(R.id.coordinator) as Coordinator<*, *>
    }
}