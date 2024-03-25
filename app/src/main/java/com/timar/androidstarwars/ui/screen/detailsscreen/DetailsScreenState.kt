package com.timar.androidstarwars.ui.screen.detailsscreen

import com.timar.androidstarwars.domain.model.BaseModel

data class DetailsScreenState (
    val data: BaseModel = BaseModel("", "", 0),
    val isLoading: Boolean = false
)