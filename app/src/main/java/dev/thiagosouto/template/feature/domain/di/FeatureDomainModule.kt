package dev.thiagosouto.template.feature.domain.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.thiagosouto.template.feature.domain.FeatureDataHolderProvider
import dev.thiagosouto.template.feature.domain.FeatureRepository
import dev.thiagosouto.template.feature.domain.FeatureRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal abstract class FeatureDomainModule {

    @Binds
    @Singleton
    abstract fun bindFeatureRepository(
        featureRepositoryImpl: FeatureRepositoryImpl
    ): FeatureRepository

    @Binds
    @Singleton
    abstract fun bindFeatureDataHolderProvider(
        repository: FeatureRepository
    ): FeatureDataHolderProvider
}