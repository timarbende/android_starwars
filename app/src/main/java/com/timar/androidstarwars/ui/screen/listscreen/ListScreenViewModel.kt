package com.timar.androidstarwars.ui.screen.listscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.cachedIn
import androidx.paging.map
import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.transformers.toBaseModel
import com.timar.androidstarwars.domain.network.StarWarsClient
import com.timar.androidstarwars.ui.util.ContentType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel(assistedFactory = ListScreenViewModel.ListScreenViewModelFactory::class)
class ListScreenViewModel @AssistedInject constructor(
    @Assisted val contentType: ContentType,
    private val swapiClient: StarWarsClient,
    private val characterPager: Pager<Int, CharacterEntity>,
    private val starshipsPager: Pager<Int, CharacterEntity>,
    private val planetsPager: Pager<Int, CharacterEntity>
): ViewModel() {
    private val _state = MutableStateFlow(ListScreenState())
    val state = _state.asStateFlow()

    val pager = when(contentType){
        ContentType.Characters -> characterPager
        ContentType.StarShips -> starshipsPager
        ContentType.Planets -> planetsPager
    }

    val pagingFlow = pager
        .flow
        .map { pagingData ->
            pagingData.map{it.toBaseModel()}
        }
        .cachedIn(viewModelScope)

    @AssistedFactory
    interface ListScreenViewModelFactory{
        fun create(contentType: ContentType): ListScreenViewModel
    }

    init{
        /*_state.update {
            it.copy(
                isLoading = true
            )
        }
        viewModelScope.launch {
            when (contentType) {
                ContentType.Characters -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            data = swapiClient.getCharactersList()
                        )
                    }
                }

                ContentType.StarShips -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            data = swapiClient.getStarShipsList()
                        )
                    }
                }

                ContentType.Planets -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            data = swapiClient.getPlanetsList()
                        )
                    }
                }
            }
        }*/
    }
}