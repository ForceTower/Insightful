package dev.forcetower.instascan.view.importer

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import dev.forcetower.instascan.core.model.dto.AccountDTO
import dev.forcetower.instascan.core.model.dto.InstagramAccountDTO
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.databinding.FragmentAccountImporterBinding
import dev.forcetower.instascan.view.login.LoginViewModel
import dev.forcetower.toolkit.components.BaseDialogFragment
import dev.forcetower.toolkit.lifecycle.EventObserver
import timber.log.Timber
import java.util.*

@AndroidEntryPoint
class ImporterSheet : BaseDialogFragment() {
    private lateinit var binding: FragmentAccountImporterBinding
    private val viewModel by activityViewModels<LoginViewModel>()
    private val args by navArgs<ImporterSheetArgs>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        return FragmentAccountImporterBinding.inflate(inflater, container, false).also {
            binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val accounts = listOf(*args.accounts)
        val importerAdapter = ImporterAdapter(viewModel)
        importerAdapter.submitList(accounts)
        binding.recycler.apply {
            adapter = importerAdapter
        }

        viewModel.onAccountImported.observe(this, EventObserver {
            dismiss()
        })
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        Timber.d("Cancelled! ...")
        viewModel.logout()
    }
}