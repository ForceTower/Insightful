package dev.forcetower.instascan.view.home

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.forcetower.instascan.R
import dev.forcetower.instascan.databinding.ItemFeatureShortcutBinding
import dev.forcetower.toolkit.extensions.inflate

class FeatureAdapter : ListAdapter<HomeFeature, FeatureAdapter.FeatureHolder>(DiffCallback) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FeatureAdapter.FeatureHolder {
        return FeatureHolder(parent.inflate(R.layout.item_feature_shortcut))
    }

    override fun onBindViewHolder(holder: FeatureAdapter.FeatureHolder, position: Int) {
        holder.binding.feature = getItem(position)
    }

    inner class FeatureHolder(val binding: ItemFeatureShortcutBinding) : RecyclerView.ViewHolder(binding.root)

    private object DiffCallback: DiffUtil.ItemCallback<HomeFeature>() {
        override fun areItemsTheSame(oldItem: HomeFeature, newItem: HomeFeature) = oldItem.title == newItem.title
        override fun areContentsTheSame(oldItem: HomeFeature, newItem: HomeFeature) = oldItem == newItem
    }
}
