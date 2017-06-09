package com.jlangen.vaultbox.vaults

data class ResultOrError<out T>(val result: T? = null, val exception: Throwable? = null)