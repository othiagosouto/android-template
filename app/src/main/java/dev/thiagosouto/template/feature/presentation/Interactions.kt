package dev.thiagosouto.template.feature.presentation

/**
 * Represents UI events from application
 */
sealed class Interactions {

    /**
     * Triggered when application is open
     */
    object OpenScreen : Interactions()
}
