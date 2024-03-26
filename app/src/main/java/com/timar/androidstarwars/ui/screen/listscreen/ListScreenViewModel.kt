package com.timar.androidstarwars.ui.screen.listscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.timar.androidstarwars.data.local.LocalDatabase
import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.local.planet.PlanetEntity
import com.timar.androidstarwars.data.local.starship.StarShipEntity
import com.timar.androidstarwars.data.transformers.toBaseModel
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.ui.util.ContentType
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = ListScreenViewModel.ListScreenViewModelFactory::class)
class ListScreenViewModel @AssistedInject constructor(
    @Assisted val contentType: ContentType,
    characterPager: Pager<Int, CharacterEntity>,
    starshipsPager: Pager<Int, StarShipEntity>,
    planetsPager: Pager<Int, PlanetEntity>,
    private val localDb: LocalDatabase
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

    fun onFavouriteButtonClick (item: BaseModel) {
        item.isFavourite = !item.isFavourite
        viewModelScope.launch {
            localDb.characterDao.updateCharacterFavourite(item.id, item.isFavourite)
        }
    }
}