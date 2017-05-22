package com.jlangen.vaultbox.screens.vault

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.architecture.BaseActivity
import com.jlangen.vaultbox.architecture.DaggerCoordinatorProvider
import com.jlangen.vaultbox.architecture.coordinators.Coordinators

class VaultActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vault)

        val toolbar = findViewById(R.id.toolbar) as Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDefaultDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.app_name)

        Coordinators.bind(findViewById(R.id.vault_view), DaggerCoordinatorProvider())
    }
}
