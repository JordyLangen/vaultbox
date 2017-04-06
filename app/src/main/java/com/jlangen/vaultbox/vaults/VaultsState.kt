package com.jlangen.vaultbox.vaults

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.FrameLayout
import com.jlangen.vaultbox.architecture.coordinators.StateRenderer

class VaultsState @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), StateRenderer<VaultsViewState> {

    override fun render(state: VaultsViewState) {
        Log.d("Vaultbox", "rendering $state")
    }
}