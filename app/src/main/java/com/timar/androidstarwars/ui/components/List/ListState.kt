package com.timar.androidstarwars.ui.components.List

import com.timar.androidstarwars.domain.model.BaseModel

data class StarWarsListState (
    val data: List<BaseModel> = emptyList(),
    val isLoading: Boolean = false
)