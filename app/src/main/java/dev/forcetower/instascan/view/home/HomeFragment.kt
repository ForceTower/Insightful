package dev.forcetower.instascan.view.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import dev.forcetower.instascan.R
import dev.forcetower.instascan.databinding.FragmentHomeBinding
import dev.forcetower.toolkit.components.BaseFragment

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHomeBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val headerAdapter = HeaderAdapter()
        val featureAdapter = FeatureAdapter()

        val adapter = ConcatAdapter(headerAdapter, featureAdapter)
        binding.homeRecycler.adapter = adapter

        featureAdapter.submitList(listOf(
            HomeFeature("Analise de Mídias", "Descubra estatisticas interessantes sobre suas mídias", R.drawable.likes_lookup, R.color.likes_lookup)
        ))
    }
}