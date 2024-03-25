package com.timar.androidstarwars.ui.screen.detailsscreen

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.domain.network.StarWarsClient
import com.timar.androidstarwars.ui.util.ContentType
import com.timar.androidstarwars.ui.util.DetailsDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsScreenViewModel @Inject constructor(
    swapiClient: StarWarsClient,
    savedStateHandle: SavedStateHandle
) : ViewModel(){
    private val _state = MutableStateFlow(DetailsScreenState())
    val state = _state.asStateFlow()

    init{
        _state.update{
            it.copy(
                isLoading = true
            )
        }

        val contentType = savedStateHandle.get<ContentType>(DetailsDestination.contentTypeArgument)
        val id = savedStateHandle.get<String>(DetailsDestination.itemIdArgument)

        if(id != null && contentType != null) {
            viewModelScope.launch {
                var result: BaseModel? = null
                when(contentType){
                    ContentType.Characters -> {
                        result = swapiClient.getCharacterDetails(id)
                    }
                    ContentType.StarShips -> {

                    }
                    ContentType.Planets -> {

                    }
                }
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
            }
        }
    }
}