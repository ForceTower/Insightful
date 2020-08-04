package dev.forcetower.instascan.view.login

import android.content.Intent
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dev.forcetower.instascan.core.model.dto.InstagramAccountDTO
import dev.forcetower.instascan.core.source.repository.LoginRepository
import dev.forcetower.instascan.view.importer.ImporterActions
import dev.forcetower.toolkit.lifecycle.Event
import kotlinx.coroutines.launch

class LoginViewModel @ViewModelInject constructor(
    private val repository: LoginRepository
) : ViewModel(), LoginActions, ImporterActions {
    private val callbackManager = CallbackManager.Factory.create()
    private val callback = LoginCallback()

    private val _onFacebookLogin = MutableLiveData<Event<Unit>>()
    private val _onFacebookLoginCancel = MutableLiveData<Event<Unit>>()
    private val _onFacebookLoginError = MutableLiveData<Event<FacebookException>>()
    private val _onFacebookConnected = MutableLiveData<Event<LoginResult>>()
    private val _onReceivedAccounts = MediatorLiveData<List<InstagramAccountDTO>>()
    private val _onAccountImported = MutableLiveData<Event<InstagramAccountDTO>>()
    private val _onImportCompleted = MutableLiveData<Event<InstagramAccountDTO>>()

    val onFacebookLogin: LiveData<Event<Unit>> = _onFacebookLogin
    val onFacebookLoginCancel: LiveData<Event<Unit>> = _onFacebookLoginCancel
    val onFacebookLoginError: LiveData<Event<FacebookException>> = _onFacebookLoginError
    val onFacebookConnected: LiveData<Event<LoginResult>> = _onFacebookConnected
    val onAccountImported: LiveData<Event<InstagramAccountDTO>> = _onAccountImported
    val onImportCompleted: LiveData<Event<InstagramAccountDTO>> = _onImportCompleted
    val onReceivedAccounts: LiveData<List<InstagramAccountDTO>> = _onReceivedAccounts

    init {
        LoginManager.getInstance().registerCallback(callbackManager, callback)
    }

    override fun onFacebookLogin() {
        _onFacebookLogin.value = Event(Unit)
        _onReceivedAccounts.value = null
    }

    fun connect(token: String) {
        val source = repository.login(token).asLiveData()
        _onReceivedAccounts.addSource(source) {
            _onReceivedAccounts.value = it
            _onReceivedAccounts.removeSource(source)
        }
    }

    fun logout() {
        LoginManager.getInstance().logOut()
    }

    override fun onImportAccount(account: InstagramAccountDTO) {
        val token = AccessToken.getCurrentAccessToken()
        if (token != null && !token.isExpired) {
            _onAccountImported.value = Event(account)
            _onImportCompleted.value = Event(account)
            viewModelScope.launch {
                repository.importAccount(account, token)
            }
        }
    }

    fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
    }

    private inner class LoginCallback : FacebookCallback<LoginResult> {
        override fun onSuccess(result: LoginResult) {
            _onFacebookConnected.value = Event(result)
        }

        override fun onCancel() {
            _onFacebookLoginCancel.value = Event(Unit)
        }

        override fun onError(error: FacebookException) {
            _onFacebookLoginError.value = Event(error)
        }
    }
}