package com.jlangen.vaultbox.vaults.vault

import android.content.Context
import android.support.design.widget.CoordinatorLayout
import android.support.design.widget.Snackbar
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.jakewharton.rxrelay2.PublishRelay
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.architecture.state.StateRenderer
import com.jlangen.vaultbox.vaults.vault.VaultViewState.UnlockState
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import kotlinx.android.synthetic.main.activity_vault.view.*
import java.util.concurrent.TimeUnit

class VaultView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : CoordinatorLayout(context, attrs, defStyleAttr), StateRenderer<VaultViewState> {

    private val onOpenVaultClickRelay: PublishRelay<String> = PublishRelay.create()
    private val onSearchIntentRelay: PublishRelay<String> = PublishRelay.create()
    private val adapter = VaultEntryAdapter(emptyList())
    private lateinit var searchTextWatcher: TextWatcher

    val openVaultIntents: Observable<String>
        get() = onOpenVaultClickRelay

    val searchIntents: Observable<String>
        get() = onSearchIntentRelay

    override fun onFinishInflate() {
        super.onFinishInflate()

        vault_entries_recycler_view.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        vault_entries_recycler_view.adapter = adapter

        vault_unlock_button.setOnClickListener {
            val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(vault_unlock_password_field.windowToken, 0)
            onOpenVaultClickRelay.accept(vault_unlock_password_field.text.toString())
        }

        searchTextWatcher = object : TextWatcher {
            override fun afterTextChanged(editable: Editable) {}

            override fun beforeTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(text: CharSequence, start: Int, count: Int, after: Int) {
                onSearchIntentRelay.accept(text.toString())
            }
        }

        vault_search_edit_text.addTextChangedListener(searchTextWatcher)
    }

    override fun onDetachedFromWindow() {
        vault_search_edit_text.removeTextChangedListener(searchTextWatcher)
        super.onDetachedFromWindow()
    }

    override fun render(state: VaultViewState) {
        toolbar.title = state.vault.name

        when (state.unlockState) {
            UnlockState.Opened -> showVaultEntries(state)
            UnlockState.Unlocking -> showUnlockingAnimation(state)
            else -> showUnlockView(state)
        }
    }

    private fun showUnlockingAnimation(state: VaultViewState) {
        // todo
    }

    private fun showVaultEntries(state: VaultViewState) {
        updateVaultEntries(state)
    }

    private fun updateVaultEntries(state: VaultViewState) {
        vault_search_edit_text.visibility = View.VISIBLE
        vault_unlock_view.visibility = View.GONE
        val diffResult = DiffUtil.calculateDiff(VaultEntryDiffCallback(adapter.vaultEntries, state.filteredEntries), true)
        adapter.vaultEntries = state.filteredEntries
        diffResult.dispatchUpdatesTo(adapter)
    }

    private fun showUnlockView(state: VaultViewState) {
        vault_search_edit_text.visibility = View.GONE
        vault_unlock_view.visibility = View.VISIBLE

        if (state.unlockState == UnlockState.InvalidPasswordUnlockAttempt) {
            Snackbar.make(this, R.string.vault_unlock_error_invalid_password, Snackbar.LENGTH_SHORT).show()
        }
    }
}