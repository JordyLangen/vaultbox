package com.jlangen.vaultbox.screens.vault

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jlangen.vaultbox.R

class VaultEntryAdapter(var vaultEntries: List<VaultEntry>) : RecyclerView.Adapter<VaultEntryAdapter.VaultEntryViewHolder>() {

    override fun getItemCount(): Int {
        return vaultEntries.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaultEntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_vault_entry, parent, false)
        return VaultEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: VaultEntryViewHolder, position: Int) {
        val entry = vaultEntries[position]

        holder.titleView.text = entry.title
    }

    class VaultEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleView = itemView.findViewById(R.id.vault_entry_title) as TextView
    }
}