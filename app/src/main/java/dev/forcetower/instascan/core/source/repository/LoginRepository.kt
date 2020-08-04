package dev.forcetower.instascan.core.source.repository

import dev.forcetower.instascan.core.source.local.InstrackDB
import dev.forcetower.instascan.core.source.remote.FacebookGraph
import kotlinx.coroutines.flow.flow
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LoginRepository @Inject constructor(
    private val database: InstrackDB,
    private val service: FacebookGraph
) {

    fun login(token: String) = flow {
        val accounts = service.accounts(token).data
        Timber.d("Accounts... $accounts")
        val instagrams = accounts.mapNotNull {
            service.instagramBusinessAccount(it.id, it.accessToken).instagramBusinessAccount
        }
        emit(instagrams)
    }
}