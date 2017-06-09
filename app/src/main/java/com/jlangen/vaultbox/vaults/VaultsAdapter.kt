package com.jlangen.vaultbox.vaults

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jakewharton.rxrelay2.PublishRelay
import com.jlangen.vaultbox.R
import com.jlangen.vaultbox.vaults.Vault

class VaultsAdapter(var vaults: List<Vault>) : RecyclerView.Adapter<VaultsAdapter.VaultViewHolder>() {

    val onVaultClickRelay: PublishRelay<Vault> = PublishRelay.create<Vault>()

    override fun getItemCount(): Int {
        return vaults.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaultViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_vault, parent, false)
        return VaultViewHolder(view)
    }

    override fun onBindViewHolder(holder: VaultViewHolder, position: Int) {
        val vault = vaults[position]
        holder.nameView.text = vault.name
        holder.pathView.text = vault.path

        holder.itemView.setOnClickListener { onVaultClickRelay.accept(vault); }
    }

    class VaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.findViewById(R.id.vault_name) as TextView
        val pathView = itemView.findViewById(R.id.vault_path) as TextView
    }
}