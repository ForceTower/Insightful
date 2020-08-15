package dev.forcetower.instascan.view.home

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.forcetower.instascan.R
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.databinding.ItemAccountHeaderBinding
import dev.forcetower.toolkit.extensions.inflate

class HeaderAdapter : RecyclerView.Adapter<HeaderAdapter.HeaderHolder>() {
    private val differ = AsyncListDiffer(this, DiffCallback)

    var account: Account? = null
    set(value) {
        field = value
        differ.submitList(buildMergedList(acc = value))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderHolder {
        return HeaderHolder.AccountHolder(parent.inflate(R.layout.item_account_header))
    }

    override fun onBindViewHolder(holder: HeaderHolder, position: Int) {
        // val element = differ.currentList[position]
        when (holder) {
            is HeaderHolder.AccountHolder -> {
                holder.binding.account = account
            }
        }
    }

    override fun getItemCount() = 1

    private fun buildMergedList(acc: Account?): List<Any?> {
        return listOf(acc)
    }

    sealed class HeaderHolder(view: View) : RecyclerView.ViewHolder(view) {
        class AccountHolder(val binding: ItemAccountHeaderBinding) : HeaderHolder(binding.root)
    }

    private object DiffCallback: DiffUtil.ItemCallback<Any>() {
        override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
            return oldItem is Account && newItem is Account
        }

        override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
           return when {
               oldItem is Account && newItem is Account -> oldItem == newItem
               else -> false
           }
        }
    }
}