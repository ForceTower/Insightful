package dev.forcetower.instascan.core.source.repository

import com.facebook.AccessToken
import dev.forcetower.instascan.core.model.dto.InstagramAccountDTO
import dev.forcetower.instascan.core.model.storage.Account
import dev.forcetower.instascan.core.model.storage.AccountAccess
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

    suspend fun importAccount(data: InstagramAccountDTO, token: AccessToken) {
        val access = AccountAccess(
            data.id,
            data.igId,
            data.name,
            token.token,
            data.username,
            data.biography,
            data.profilePictureUrl,
            data.followersCount,
            data.followsCount,
            true
        )

        val account = Account(
            data.id,
            data.igId,
            data.name,
            data.username,
            data.biography,
            data.profilePictureUrl,
            data.followersCount,
            data.followsCount
        )

        database.access().insert(access)
        database.account().insert(account)
    }

    fun getAccess() = database.access().getAccessCount()
}