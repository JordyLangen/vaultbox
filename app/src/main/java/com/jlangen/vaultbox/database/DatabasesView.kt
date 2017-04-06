package com.jlangen.vaultbox.database

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.jlangen.vaultbox.architecture.coordinators.ViewRenderer

class DatabasesView @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr), ViewRenderer<DatabasesViewState> {

    override fun render(state: DatabasesViewState) {

    }
}