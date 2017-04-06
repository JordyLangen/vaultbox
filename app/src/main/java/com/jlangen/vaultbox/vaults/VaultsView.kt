package com.jlangen.vaultbox.vaults

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.jlangen.vaultbox.architecture.coordinators.ViewRenderer

class VaultsView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), ViewRenderer<VaultsViewState> {

    override fun render(state: VaultsViewState) {

    }
}