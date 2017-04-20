package com.jlangen.vaultbox.architecture.coordinators

import android.view.View

/**
 * A Coordinator is attached to one view at a time.

 * What you do from there is up to you.

 * @see CoordinatorProvider
 */
open class Coordinator<in TState, in TStateRenderer : StateRenderer<TState>> {

    /**
     * True from just before attach until just after detach.
     */
    var isAttached: Boolean = false
        internal set

    /**
     * Called when the view is attached to a Window.

     * Default implementation does nothing.

     * @see View.onAttachedToWindow
     */
    open fun attach(view: TStateRenderer) {
    }

    /**
     * Called when the view is detached from a Window.

     * Default implementation does nothing.

     * @see View.onDetachedFromWindow
     */
    open fun detach(view: TStateRenderer) {
    }
}