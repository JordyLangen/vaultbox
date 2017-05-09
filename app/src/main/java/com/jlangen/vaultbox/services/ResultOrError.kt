package com.jlangen.vaultbox.services

data class ResultOrError<out T>(val result: T? = null, val exception: Throwable? = null)