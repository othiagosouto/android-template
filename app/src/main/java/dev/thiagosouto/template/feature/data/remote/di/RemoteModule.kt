package dev.thiagosouto.template.feature.data.remote.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.thiagosouto.template.feature.data.remote.BaseUrl
import dev.thiagosouto.template.feature.data.remote.FeatureRemoteApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object RemoteProvidesModule {

    @Singleton
    @Provides
    fun provideRetrofit(baseUrl: BaseUrl): Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl.value)
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()

    @Singleton
    @Provides
    fun provideFeatureRemoteApi(retrofit: Retrofit): FeatureRemoteApi =
        retrofit.create(FeatureRemoteApi::class.java)

}

@Module
@InstallIn(SingletonComponent::class)
object RemoteBaseUrlModule {

    @Singleton
    @Provides
    fun provideBaseUrl(): BaseUrl = BaseUrl("https://api.github.com/")
}
