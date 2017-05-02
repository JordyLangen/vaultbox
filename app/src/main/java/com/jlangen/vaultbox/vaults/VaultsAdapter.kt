package com.jlangen.vaultbox.vaults

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.jlangen.vaultbox.R

class VaultsAdapter(var vaults: List<Vault>) : RecyclerView.Adapter<VaultsAdapter.VaultViewHolder>() {

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
    }

    class VaultViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val nameView = itemView.findViewById(R.id.vault_name) as TextView
        val pathView = itemView.findViewById(R.id.vault_path) as TextView
    }
}