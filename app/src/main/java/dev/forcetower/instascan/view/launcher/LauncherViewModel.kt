package dev.forcetower.instascan.view.launcher

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dev.forcetower.instascan.core.source.repository.LoginRepository
import dev.forcetower.toolkit.lifecycle.Event

class LauncherViewModel @ViewModelInject constructor(
    repository: LoginRepository
) : ViewModel() {
    enum class LaunchDestination {
        HOME,
        LOGIN
    }

    private val _launchDestination = MediatorLiveData<Event<LaunchDestination>>()
    val launchDestination: LiveData<Event<LaunchDestination>> = _launchDestination

    init {
        val source = repository.getAccess().asLiveData()
        _launchDestination.addSource(source) {
            _launchDestination.removeSource(source)
            _launchDestination.value = Event(
                if (it == 0) LaunchDestination.LOGIN else LaunchDestination.HOME
            )
        }
    }
}