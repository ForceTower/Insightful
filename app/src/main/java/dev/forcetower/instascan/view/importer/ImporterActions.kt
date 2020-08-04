package dev.forcetower.instascan.view.importer

import dev.forcetower.instascan.core.model.dto.InstagramAccountDTO

interface ImporterActions {
    fun onImportAccount(account: InstagramAccountDTO)
}