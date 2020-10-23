package dev.forcetower.instascan.view.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.forcetower.instascan.core.source.repository.SyncRepository
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(
    private val sync: SyncRepository
) : ViewModel() {
    fun syncCurrent() {
        viewModelScope.launch {
            sync.syncCurrent()
        }
    }
}