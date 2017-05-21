package com.jlangen.vaultbox.screens.vault

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.jakewharton.rxrelay2.PublishRelay
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.architecture.state.StateRenderer
import com.jlangen.vaultbox.screens.vault.VaultViewState.UnlockState
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_vault.view.*

class VaultView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr), StateRenderer<VaultViewState> {

    private val onOpenVaultClickRelay: PublishRelay<String> = PublishRelay.create<String>()
    private val adapter = VaultEntryAdapter(emptyList())

    val openVaultIntents: Observable<String>
        get() = onOpenVaultClickRelay

    override fun onFinishInflate() {
        super.onFinishInflate()

        vault_entries_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vault_entries_recycler_view.adapter = adapter

        vault_unlock_button.setOnClickListener {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(vault_unlock_password_field.windowToken, 0)
            onOpenVaultClickRelay.accept(vault_unlock_password_field.text.toString())
        }
    }

    override fun render(state: VaultViewState) {
        if (state.unlockState == UnlockState.Opened) {
            showVaultEntries(state)
        } else {
            showUnlockView(state)
        }
    }

    private fun showVaultEntries(state: VaultViewState) {
        vault_unlock_view.visibility = View.GONE
        val diffResult = DiffUtil.calculateDiff(VaultEntryDiffCallback(adapter.vaultEntries, state.vault.entries), true)
        adapter.vaultEntries = state.vault.entries
        adapter.updateGroupIndicators()
        diffResult.dispatchUpdatesTo(adapter)
    }

    private fun showUnlockView(state: VaultViewState) {
        vault_unlock_view.visibility = View.VISIBLE

        if (state.unlockState == UnlockState.InvalidPasswordUnlockAttempt) {
            Snackbar.make(this, R.string.vault_unlock_error_invalid_password, Snackbar.LENGTH_SHORT).show()
        }
    }
}