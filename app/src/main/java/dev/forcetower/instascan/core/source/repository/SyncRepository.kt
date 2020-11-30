package dev.forcetower.instascan.core.source.repository

import dev.forcetower.instascan.core.model.storage.Media
import dev.forcetower.instascan.core.model.storage.Story
import dev.forcetower.instascan.core.source.local.InstrackDB
import dev.forcetower.instascan.core.source.remote.FacebookGraph
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SyncRepository @Inject constructor(
    private val database: InstrackDB,
    private val service: FacebookGraph
) {
    suspend fun syncAll() {
        val ids = database.access().getAllIds()
        ids.forEach { syncProfile(it) }
    }

    suspend fun syncCurrent() {
        val account = database.access().getCurrentAccountAccess()
        if (account == null) {
            Timber.d("No currently selected account...")
            return
        }

        syncProfile(account.id)
    }

    suspend fun syncProfile(id: String) {
        val access = database.access().getAccountAccess(id)
        if (access == null) {
            Timber.d("The account with id $id was deleted :)")
            return
        }

        Timber.d("Currently sync ${access.username} ${access.name}")
        syncMedias(id)
        syncStories(id)
    }

    private suspend fun syncMedias(id: String) {
        val response = service.medias(id)
        val complete = mutableListOf<Media>()

        val data = response.media?.data?.map { it.toMedia() } ?: emptyList()
        complete += data

        var after = response.media?.paging?.cursors?.after
        var hasNext = after != null && data.isNotEmpty()

        while (hasNext && after != null) {
            val continuation = service.medias(id, after)
            val appendData = continuation.media?.data?.map { it.toMedia() } ?: emptyList()
            complete += appendData

            after = continuation.media?.paging?.cursors?.after
            hasNext = after != null && appendData.isNotEmpty()
        }

        Timber.d("All medias fetched: $complete")
        database.media().insertOrUpdate(complete)
    }

    private suspend fun syncStories(id: String) {
        val response = service.stories(id)
        val complete = mutableListOf<Story>()

        val data = response.stories?.data?.map { it.toStoryData() } ?: emptyList()
        complete += data

        var after = response.stories?.paging?.cursors?.after
        var hasNext = after != null && data.isNotEmpty()

        while (hasNext && after != null) {
            val continuation = service.stories(id, after)
            val appendData = continuation.stories?.data?.map { it.toStoryData() } ?: emptyList()
            complete += appendData

            after = continuation.stories?.paging?.cursors?.after
            hasNext = after != null && appendData.isNotEmpty()
        }

        Timber.d("All stories fetched: $complete")
        database.story().insertOrUpdate(complete)
    }


}