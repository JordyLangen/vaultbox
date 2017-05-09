package com.jlangen.vaultbox.screens.vaults

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.jakewharton.rxrelay2.PublishRelay
import com.jlangen.vaultbox.architecture.state.StateRenderer
import com.jlangen.vaultbox.screens.vault.Vault
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_vaults_overview.view.*

class VaultsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), StateRenderer<VaultsViewState> {

    private val adapter = VaultsAdapter(emptyList())

    val showVaultIntents: Observable<Vault>
        get() = adapter.onVaultClickRelay

    override fun onFinishInflate() {
        super.onFinishInflate()
        vaults_view_recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vaults_view_recycler.adapter = adapter
    }

    override fun render(state: VaultsViewState) {
        if (state.isLoading) {
            vaults_view_progressbar.visibility = View.VISIBLE
            updateAdapter(emptyList())
        } else {
            vaults_view_progressbar.visibility = View.GONE
            updateAdapter(state.vaults)
        }
    }

    private fun updateAdapter(vaults: List<Vault>) {
        val diffResult = DiffUtil.calculateDiff(VaultDiffCallback(adapter.vaults, vaults), true)
        adapter.vaults = vaults
        diffResult.dispatchUpdatesTo(adapter)
    }
}