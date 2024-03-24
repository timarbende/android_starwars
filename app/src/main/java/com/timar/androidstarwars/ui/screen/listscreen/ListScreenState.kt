package com.timar.androidstarwars.ui.screen.listscreen

import com.timar.androidstarwars.domain.model.BaseModel

data class ListScreenState (
    val data: List<BaseModel> = emptyList(),
    val isLoading: Boolean = false
)