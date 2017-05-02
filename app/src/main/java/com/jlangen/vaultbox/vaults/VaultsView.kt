package com.jlangen.vaultbox.vaults

import android.content.Context
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.widget.FrameLayout
import com.jlangen.vaultbox.architecture.coordinators.StateRenderer
import kotlinx.android.synthetic.main.activity_vaults_overview.view.*

class VaultsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), StateRenderer<VaultsViewState> {

    private val adapter = VaultsAdapter(emptyList())

    override fun onFinishInflate() {
        super.onFinishInflate()
        vaults_view_recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vaults_view_recycler.adapter = adapter
    }

    override fun render(state: VaultsViewState) {
        updateAdapter(state)
    }

    private fun updateAdapter(state: VaultsViewState) {
        val diffResult = DiffUtil.calculateDiff(VaultDiffCallback(adapter.vaults, state.vaults), true)
        adapter.vaults = state.vaults
        diffResult.dispatchUpdatesTo(adapter)
    }
}