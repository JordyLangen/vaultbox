package com.jlangen.vaultbox.screens.vault

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.RelativeLayout
import com.jakewharton.rxrelay2.PublishRelay
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.architecture.state.StateRenderer
import com.jlangen.vaultbox.screens.vault.VaultViewState.UnlockState
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_vault.view.*

class VaultView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), StateRenderer<VaultViewState> {

    private val onOpenVaultClickRelay: PublishRelay<String> = PublishRelay.create<String>()

    val openVaultIntents: Observable<String>
        get() = onOpenVaultClickRelay

    override fun onFinishInflate() {
        super.onFinishInflate()

        vault_unlock_button.setOnClickListener {
            onOpenVaultClickRelay.accept(vault_unlock_password_input.text.toString())
        }
    }

    override fun render(state: VaultViewState) {
        if (state.unlockState == UnlockState.Opened) {
            vault_unlock_view.visibility = View.GONE
        } else {
            vault_unlock_view.visibility = View.VISIBLE

            if (state.unlockState == UnlockState.InvalidPasswordUnlockAttempt) {
                vault_unlock_password_input_layout.error = context.getString(R.string.vault_unlock_error_invalid_password)
            } else {
                vault_unlock_password_input_layout.error = null
            }
        }
    }
}