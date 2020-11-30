package dev.forcetower.instascan.core.source.remote

import dev.forcetower.instascan.core.model.dto.*
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
        @Query("remove_ignore") after: String? = null,
        @Query("remove_ignore") limit: Int = 200,
        @Query("remove_ignore") afterStr: String = if (after != null) ".after($after)" else "",
        @Query("fields") fields: String = "media$afterStr.limit($limit){id,caption,ig_id,comments_count,like_count,media_type,thumbnail_url,media_url,owner,timestamp,permalink,filter_name}"
    ): MediaResponse

    @GET("{profileId}")
    suspend fun stories(
        @Path("profileId") profileId: String,
        @Query("remove_ignore") after: String? = null,
        @Query("remove_ignore") limit: Int = 21,
        @Query("remove_ignore") afterStr: String = if (after != null) ".after($after)" else "",
        @Query("fields") fields: String = "stories${afterStr}.limit($limit){insights.metric(exits,impressions,reach,replies,taps_forward,taps_back),id,ig_id,comments_count,like_count,media_type,thumbnail_url,media_url,owner,timestamp,permalink}"
    ): StoryResponse
}