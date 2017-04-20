package com.jlangen.vaultbox.architecture.coordinators

interface StateRenderer<in TState> {

    fun render(state: TState)
}
