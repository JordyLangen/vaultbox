package com.jlangen.vaultbox.architecture.coordinators

import android.view.View

interface CoordinatorProvider {

    /**
     * Called to obtain a [Coordinator] for a View.

     * Called from [Coordinators.bind]. Whether or not Coordinator instances are reused is up
     * to the implementer, but a Coordinator instance may only be bound to one View instance at a
     * time.

     * @return null if the view has no associated coordinator
     */
    fun provideCoordinator(view: View): Coordinator<*, *>?
}