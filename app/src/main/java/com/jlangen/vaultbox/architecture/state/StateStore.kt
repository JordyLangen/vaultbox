package com.jlangen.vaultbox.architecture.state

import android.content.Context

object StateStore {

    // works based on the assumption that view id + activity name is unique
    // if you use an id twice in the same view tree, well that's just on you

    private val states: MutableList<StateHolder<*>> = mutableListOf()

    fun <TState> update(id: Int, state: TState, context: Context) {
        val contextName = context.javaClass.name
        val currentState = states.find { it.viewId == id && it.contextName == contextName }
        if (currentState == null) {
            states.add(StateHolder(id, state, context.javaClass.name))
        } else if (!currentState.activityFinished) {
            states.remove(currentState)
            states.add(StateHolder(id, state, context.javaClass.name))
        } else {
            states.remove(currentState)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <TState> resolve(id: Int, context: Context): TState? {
        val contextName = context.javaClass.name
        val stateHolder = states.find { it.viewId == id && it.contextName == contextName }

        if (stateHolder == null) {
            return null
        } else {
            return stateHolder.state as TState
        }
    }

    fun markFinished(context: Context) {
        val contextName = context.javaClass.name
        states.forEach { stateHolder ->
            stateHolder.activityFinished = contextName == stateHolder.contextName
        }
    }

    private data class StateHolder<out TState>(val viewId: Int, val state: TState, val contextName: String, var activityFinished: Boolean = false)
}