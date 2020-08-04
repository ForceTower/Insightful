package dev.forcetower.instascan.dagger

import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dev.forcetower.instascan.core.source.remote.FacebookGraph
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun provideTMDbService(client: OkHttpClient, gson: Gson): FacebookGraph {
        return Retrofit.Builder()
            .baseUrl("https://graph.facebook.com/v4.0/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(FacebookGraph::class.java)
    }
}