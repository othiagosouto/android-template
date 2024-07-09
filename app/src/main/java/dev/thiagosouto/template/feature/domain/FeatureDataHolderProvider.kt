package dev.thiagosouto.template.feature.domain

fun interface FeatureDataHolderProvider {
    suspend fun provide(): FeatureDataHolder
}