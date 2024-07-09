package dev.thiagosouto.template.feature.data.remote

import dev.thiagosouto.template.feature.domain.FeatureDataHolder
import javax.inject.Inject

internal class FeatureRemoteDataSource @Inject constructor(
    private val featureRemoteApi: FeatureRemoteApi
) {
    suspend fun item(): FeatureDataHolder =
        FeatureDataHolder(featureRemoteApi.item().name)
}
