package com.jlangen.vaultbox.vaults

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.VaultboxApplication
import com.jlangen.vaultbox.architecture.BaseActivity
import com.jlangen.vaultbox.architecture.DaggerCoordinatorProvider
import com.jlangen.vaultbox.architecture.coordinators.Coordinators
import javax.inject.Inject

class VaultOverviewActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vaults_overview)

        Coordinators.bind(findViewById(R.id.vaults_view), DaggerCoordinatorProvider())
    }
}
