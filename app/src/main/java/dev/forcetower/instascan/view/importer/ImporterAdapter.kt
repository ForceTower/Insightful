package dev.forcetower.instascan.view.importer

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.forcetower.instascan.R
import dev.forcetower.instascan.core.model.dto.InstagramAccountDTO
import dev.forcetower.instascan.databinding.ItemAccountImportBinding
import dev.forcetower.toolkit.extensions.inflate

class ImporterAdapter : ListAdapter<InstagramAccountDTO, ImporterAdapter.AccountHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountHolder {
        return AccountHolder(parent.inflate(R.layout.item_account_import))
    }

    override fun onBindViewHolder(holder: AccountHolder, position: Int) {
        holder.binding.account = getItem(position)
    }

    inner class AccountHolder(val binding: ItemAccountImportBinding) : RecyclerView.ViewHolder(binding.root)
    private object DiffCallback : DiffUtil.ItemCallback<InstagramAccountDTO>() {
        override fun areItemsTheSame(
            oldItem: InstagramAccountDTO,
            newItem: InstagramAccountDTO
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: InstagramAccountDTO,
            newItem: InstagramAccountDTO
        ) = oldItem == newItem
    }
}
