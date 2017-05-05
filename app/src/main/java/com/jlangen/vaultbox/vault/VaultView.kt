package com.jlangen.vaultbox.vault

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.jlangen.vaultbox.architecture.state.StateRenderer

class VaultView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : RelativeLayout(context, attrs, defStyleAttr), StateRenderer<VaultViewState> {

    override fun render(state: VaultViewState) {

    }
}