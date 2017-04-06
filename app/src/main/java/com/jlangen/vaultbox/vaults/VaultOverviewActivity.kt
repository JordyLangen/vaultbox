package com.jlangen.vaultbox.vaults

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.VaultboxApplication
import com.jlangen.vaultbox.architecture.DaggerCoordinatorProvider
import com.jlangen.vaultbox.architecture.coordinators.Coordinators
import javax.inject.Inject

class VaultOverviewActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseRepository: VaultRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VaultboxApplication.component.inject(this)

        // todo: permissions

        // todo: move to base activity
        Coordinators.bind(findViewById(R.id.main_view), DaggerCoordinatorProvider())
    }
}
