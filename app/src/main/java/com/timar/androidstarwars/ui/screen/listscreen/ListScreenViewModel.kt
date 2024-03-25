package com.timar.androidstarwars.ui.screen.listscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.local.planet.PlanetEntity
import com.timar.androidstarwars.data.local.starship.StarShipEntity
import com.timar.androidstarwars.data.transformers.toBaseModel
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.domain.network.StarWarsClient
import com.timar.androidstarwars.ui.util.ContentType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@HiltViewModel(assistedFactory = ListScreenViewModel.ListScreenViewModelFactory::class)
class ListScreenViewModel @AssistedInject constructor(
    @Assisted val contentType: ContentType,
    private val characterPager: Pager<Int, CharacterEntity>,
    private val starshipsPager: Pager<Int, StarShipEntity>,
    private val planetsPager: Pager<Int, PlanetEntity>
): ViewModel() {
    val pagingFlow: Flow<PagingData<BaseModel>>

    @AssistedFactory
    interface ListScreenViewModelFactory{
        fun create(contentType: ContentType): ListScreenViewModel
    }

    init {
        val pager = when(contentType){
            ContentType.Characters -> characterPager
            ContentType.StarShips -> starshipsPager
            ContentType.Planets -> planetsPager
        }
        pagingFlow = pager
            .flow
            .map { pagingData ->
                pagingData.map{when(contentType){
                    ContentType.Characters -> (it as CharacterEntity).toBaseModel()
                    ContentType.StarShips -> (it as StarShipEntity).toBaseModel()
                    ContentType.Planets -> (it as PlanetEntity).toBaseModel()
                }
                }
            }
            .cachedIn(viewModelScope)
    }
}