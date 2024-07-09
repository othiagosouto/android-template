package dev.thiagosouto.template.feature.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.thiagosouto.template.feature.domain.FeatureDataHolderProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FeatureViewModel @Inject constructor(private val provider: FeatureDataHolderProvider) :
    ViewModel() {
    private val _interactions = Channel<Interactions>(Channel.UNLIMITED)
    private val _state = MutableStateFlow<FeatureState>(FeatureState.Idle)
    val state: StateFlow<FeatureState> = _state.asStateFlow()

    init {

        viewModelScope.launch {
            _interactions.consumeAsFlow().combine(state, ::Pair)
                .map { (interaction, state) ->
                    when {
                        interaction is Interactions.OpenScreen && state is FeatureState.Idle -> {
                            FeatureState.Loading
                        }

                        interaction is Interactions.OpenScreen && state is FeatureState.Loading -> {
                            val data = provider.provide()
                            FeatureState.Loaded(data.name)
                        }

                        else -> null
                    }
                }
                .collect { currentState ->
                    currentState?.let {
                        _state.emit(currentState)
                    }
                }
        }
    }

    fun interact(interaction: Interactions) {
        viewModelScope.launch {
            _interactions.send(interaction)
        }
    }
}
