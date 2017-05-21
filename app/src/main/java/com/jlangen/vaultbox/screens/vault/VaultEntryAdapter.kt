package com.jlangen.vaultbox.screens.vault

import android.graphics.Color
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.github.lzyzsd.randomcolor.RandomColor
import com.jlangen.vaultbox.R
import java.util.*

class VaultEntryAdapter(var vaultEntries: List<VaultEntry>) : RecyclerView.Adapter<VaultEntryAdapter.VaultEntryViewHolder>() {

    private var groupIndicators: MutableMap<String, Int> = mutableMapOf()

    override fun getItemCount(): Int {
        return vaultEntries.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VaultEntryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_vault_entry, parent, false)
        return VaultEntryViewHolder(view)
    }

    override fun onBindViewHolder(holder: VaultEntryViewHolder, position: Int) {
        val entry = vaultEntries[position]

        holder.groupIndicatorView.setBackgroundColor(groupIndicators[entry.group] ?: ContextCompat.getColor(holder.itemView.context, R.color.primary))
        holder.titleView.text = entry.title
        holder.groupView.text = entry.group
    }

    class VaultEntryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val groupIndicatorView = itemView.findViewById(R.id.vault_entry_group_indicator)
        val titleView = itemView.findViewById(R.id.vault_entry_title) as TextView
        val groupView = itemView.findViewById(R.id.vault_entry_group) as TextView
    }

    fun updateGroupIndicators() {
        val groups = vaultEntries
                .groupBy { it.group }
                .map { it.key }

        val colors = RandomColor().random(RandomColor.Color.BLUE, groups.size)
        groupIndicators.clear()

        groups.forEachIndexed { index, group ->
            groupIndicators.put(group, colors[index])
        }
    }
}