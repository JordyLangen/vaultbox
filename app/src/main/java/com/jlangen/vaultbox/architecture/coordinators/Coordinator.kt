package com.jlangen.vaultbox.architecture.coordinators

import android.view.View
import com.jlangen.vaultbox.architecture.state.StateRenderer

/**
 * A Coordinator is attached to one view at a time.

 * What you do from there is up to you.

 * @see CoordinatorProvider
 */
abstract class Coordinator<TState, in TStateRenderer : StateRenderer<TState>> {

    /**
     * True from just before attach until just after detach.
     */
    var isAttached: Boolean = false
        internal set

    abstract var state: TState

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