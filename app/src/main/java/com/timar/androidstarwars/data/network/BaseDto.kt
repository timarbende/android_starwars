package com.timar.androidstarwars.data.network

import com.timar.androidstarwars.domain.model.BaseModel

data class BaseDto(
    val pageInfo: PageInfo = PageInfo(),
    val data: List<BaseModel>
)
