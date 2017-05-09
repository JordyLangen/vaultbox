package com.jlangen.vaultbox.screens.vault

import android.os.Bundle
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.architecture.BaseActivity
import com.jlangen.vaultbox.architecture.DaggerCoordinatorProvider
import com.jlangen.vaultbox.architecture.coordinators.Coordinators

class VaultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vault)

        Coordinators.bind(findViewById(R.id.vault_view), DaggerCoordinatorProvider())
    }
}
