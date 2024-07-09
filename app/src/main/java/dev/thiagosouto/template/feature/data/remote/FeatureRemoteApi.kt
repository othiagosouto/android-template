package dev.thiagosouto.template.feature.data.remote

import kotlinx.serialization.Serializable
import retrofit2.http.GET


internal fun interface FeatureRemoteApi {

    @GET("random")
    suspend fun item(): Response

    @Serializable
    data class Response(val name: String)
}
