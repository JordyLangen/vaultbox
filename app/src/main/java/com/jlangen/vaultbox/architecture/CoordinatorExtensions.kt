package com.jlangen.vaultbox.architecture

import com.jlangen.vaultbox.VaultboxApplication
import com.jlangen.vaultbox.architecture.coordinators.Coordinator

val Coordinator<*, *>.navigator: Navigator
    get() = VaultboxApplication.component.resolveNavigator()