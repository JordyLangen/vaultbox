package com.jlangen.vaultbox.screens.vaults

import android.support.v7.util.DiffUtil
import com.jlangen.vaultbox.screens.vault.Vault

class VaultDiffCallback(val old: List<Vault>, val new: List<Vault>) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return old.size
    }

    override fun getNewListSize(): Int {
        return new.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return old[oldItemPosition] == new[newItemPosition]
    }
}