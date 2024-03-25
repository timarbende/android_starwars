package com.timar.androidstarwars.domain.network

import com.timar.androidstarwars.data.network.BaseDto
import com.timar.androidstarwars.data.network.PageInfo
import com.timar.androidstarwars.domain.model.BaseModel

interface StarWarsClient {
    suspend fun getCharactersList(pageInfo: PageInfo): BaseDto

    suspend fun getStarShipsList(): List<BaseModel>

    suspend fun getPlanetsList(): List<BaseModel>

    suspend fun getCharacterDetails(id: String): BaseModel?

    suspend fun getStarShipDetails(id: String): BaseModel?

    suspend fun getPlanetDetails(id: String): BaseModel?
}