package com.timar.androidstarwars.ui.screen.detailsscreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timar.androidstarwars.domain.network.StarWarsClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    private val swapiClient: StarWarsClient,
    private val savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()

    init{
        _state.update{
            it.copy(
                isLoading = true
            )
        }

        savedStateHandle.get<String>("itemId")?.let {id ->
            Log.d("DetailsViewModel", id)
            /*viewModelScope.launch {
                val result = swapiClient.getCharacterDetails(id)
                if (result != null) {
                    _state.update {
                        it.copy(
                            data = result
                        )
                    }
                }
                _state.update {
                    it.copy(
                        isLoading = false,
                    )
                }
            }*/
        }
    }
}