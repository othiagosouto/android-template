package dev.thiagosouto.template.feature.domain

import dev.thiagosouto.template.feature.data.remote.FeatureRemoteDataSource
import javax.inject.Inject

internal class FeatureRepositoryImpl @Inject constructor(
    private val remoteDataSource: FeatureRemoteDataSource
) : FeatureRepository {
    override suspend fun provide(): FeatureDataHolder = remoteDataSource.item()
}