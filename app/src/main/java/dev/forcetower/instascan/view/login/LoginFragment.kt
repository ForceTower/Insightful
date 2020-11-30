package dev.forcetower.instascan.view.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import dagger.hilt.android.AndroidEntryPoint
import dev.forcetower.instascan.R
import dev.forcetower.instascan.core.model.dto.InstagramAccountDTO
import dev.forcetower.instascan.databinding.FragmentLoginBinding
import dev.forcetower.instascan.view.importer.ImporterSheet
import dev.forcetower.toolkit.components.BaseFragment
import dev.forcetower.toolkit.lifecycle.EventObserver
import timber.log.Timber

@AndroidEntryPoint
class LoginFragment : BaseFragment() {
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentLoginBinding.inflate(inflater, container, false).also {
            binding = it
            it.actions = viewModel
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.onFacebookLogin.observe(viewLifecycleOwner, EventObserver { onFacebookLogin() })
        viewModel.onFacebookConnected.observe(viewLifecycleOwner, EventObserver {
            onLoginResult(it)
        })
        viewModel.onFacebookLoginCancel.observe(viewLifecycleOwner, EventObserver {
            showSnack(getString(R.string.facebook_login_cancel))
        })
        viewModel.onFacebookLoginError.observe(viewLifecycleOwner, EventObserver {
            showSnack(getString(R.string.facebook_login_error))
        })
        viewModel.onReceivedAccounts.observe(viewLifecycleOwner, {
            onAccountsReceived(it)
        })
        viewModel.onImportCompleted.observe(viewLifecycleOwner, EventObserver {
            onAccountImported(it)
        })
    }

    private fun onAccountImported(account: InstagramAccountDTO) {
        Timber.d("Account imported: $account")
        val directions = LoginFragmentDirections.actionLoginToHome()
        findNavController().navigate(directions)
    }

    private fun onLoginResult(result: LoginResult) {
        Timber.d("Received token ${result.accessToken.token}")
        viewModel.connect(result.accessToken.token)
    }

    private fun onAccountsReceived(accounts: List<InstagramAccountDTO>?) {
        accounts ?: return
        if (accounts.isEmpty()) {
            showSnack(getString(R.string.no_commercial_instagram_linked))
            return
        }
        Timber.d("onAccountsReceived $accounts")
        val directions = LoginFragmentDirections.actionLoginToImporter(accounts.toTypedArray())
        findNavController().navigate(directions)
    }

    private fun onFacebookLogin() {
        LoginManager.getInstance().logInWithReadPermissions(this, listOf(
            "pages_read_engagement",
            "pages_read_user_content",
            "pages_show_list",
            "business_management",
            "instagram_basic",
            "instagram_manage_insights",
            "public_profile",
            "instagram_manage_comments"
        ))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        viewModel.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }
}