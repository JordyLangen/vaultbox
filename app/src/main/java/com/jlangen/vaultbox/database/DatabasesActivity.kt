package com.jlangen.vaultbox.database

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.ViewGroup
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.VaultboxApplication
import com.jlangen.vaultbox.architecture.DaggerCoordinatorProvider
import com.jlangen.vaultbox.architecture.coordinators.Coordinators
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DatabasesActivity : AppCompatActivity() {

    @Inject
    lateinit var databaseRepository: KeepassDatabaseRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        VaultboxApplication.component.inject(this)

        // todo: permissions

        Coordinators.bind(findViewById(R.id.main_view), DaggerCoordinatorProvider())

        databaseRepository.findAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Log.d("Vaultbox", it.toString())
                }
    }
}
