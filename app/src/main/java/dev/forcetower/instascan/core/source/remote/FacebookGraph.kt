package dev.forcetower.instascan.core.source.remote

import dev.forcetower.instascan.core.model.dto.AccountDTO
import dev.forcetower.instascan.core.model.dto.CommonResponse
import dev.forcetower.instascan.core.model.dto.InstagramPageDTO
import dev.forcetower.instascan.core.model.dto.MediaResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FacebookGraph {
    @GET("me/accounts")
    suspend fun accounts(
        @Query("access_token") accessToken: String
    ): CommonResponse<List<AccountDTO>>

    @GET("{pageId}")
    suspend fun instagramBusinessAccount(
        @Path("pageId") pageId: String,
        @Query("access_token") accessToken: String,
        @Query("fields") fields: String = "instagram_business_account{followers_count,follows_count,name,username,profile_picture_url,ig_id,biography,id},name,picture"
    ): InstagramPageDTO

    @GET("{profileId}")
    suspend fun medias(
        @Path("profileId") profileId: String,
        @Query("fields") fields: String = "media.limit(250){id,caption,ig_id,comments_count,like_count,media_type,thumbnail_url,media_url,owner,timestamp,permalink,filter_name}"
    ): MediaResponse

    @GET("{profileId}")
    suspend fun mediasAfter(
        @Path("profileId") profileId: String,
        @Query("remove_ignore") after: String,
        @Query("fields") fields: String = "media.after($after).limit(250){id,caption,ig_id,comments_count,like_count,media_type,thumbnail_url,media_url,owner,timestamp,permalink,filter_name}"
    ): MediaResponse
}