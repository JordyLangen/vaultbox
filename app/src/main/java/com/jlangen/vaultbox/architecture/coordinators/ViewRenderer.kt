package com.jlangen.vaultbox.architecture.coordinators

interface ViewRenderer<in TState> {

    fun render(state: TState)
}
