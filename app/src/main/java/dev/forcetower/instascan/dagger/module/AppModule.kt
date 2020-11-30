package dev.forcetower.instascan.dagger.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.forcetower.instascan.BuildConfig
import dev.forcetower.instascan.core.source.local.InstrackDB
import dev.forcetower.instascan.core.utils.GsonUtils
import dev.forcetower.instascan.dagger.annotation.AccessTokenInjectorInterceptor
import dev.forcetower.instascan.dagger.annotation.FlagRemoveInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber
import java.time.ZonedDateTime
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideClient(
        @AccessTokenInjectorInterceptor accessInject: Interceptor,
        @FlagRemoveInterceptor flagRemove: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .followRedirects(true)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addInterceptor(flagRemove)
            .addInterceptor(accessInject)
            .addInterceptor(HttpLoggingInterceptor { message -> Timber.d(message) }.apply {
                level = if (BuildConfig.DEBUG)
                    HttpLoggingInterceptor.Level.BODY
                else
                    HttpLoggingInterceptor.Level.NONE
            })
            .build()
    }

    @Provides
    @Singleton
    @AccessTokenInjectorInterceptor
    fun accessTokenInterceptor(database: InstrackDB) = Interceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url
        val hasEmbeddedToken = originalUrl.queryParameter("access_token") != null

        if (!hasEmbeddedToken) {
            // avoid reading database if not necessary
            val token = database.access().getCurrentAccessTokenDirect()
            if (token != null) {
                val url = originalUrl
                    .newBuilder()
                    .addQueryParameter("access_token", token)
                    .build()

                val request = original
                    .newBuilder()
                    .url(url)
                    .build()

                chain.proceed(request)
            } else {
                chain.proceed(original)
            }
        } else {
            chain.proceed(original)
        }
    }

    @Provides
    @Singleton
    @FlagRemoveInterceptor
    fun flagRemoveInterceptor() = Interceptor { chain ->
        val original = chain.request()
        val originalUrl = original.url

        val url = originalUrl.newBuilder().removeAllQueryParameters("remove_ignore").build()
        val request = original.newBuilder().url(url).build()

        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .registerTypeAdapter(ZonedDateTime::class.java, GsonUtils.ZDT_DESERIALIZER)
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }
}