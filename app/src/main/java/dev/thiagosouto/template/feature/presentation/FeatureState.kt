package dev.thiagosouto.template.feature.presentation

/**
 * Feature application state
 */
sealed class FeatureState {

    /**
     * Initial state, represents default value
     */
    data object Idle : FeatureState()

    /**
     * State when application is doing heavy background work
     */
    data object Loading : FeatureState()

    /**
     * State when application finished a heavy background work
     */
    data class Loaded(val name: String) : FeatureState()
}
