package com.jlangen.vaultbox.architecture.state

interface StateRenderer<in TState> {

    fun render(state: TState)
}
