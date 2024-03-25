package com.timar.androidstarwars.domain.network

import com.timar.androidstarwars.data.network.BaseDto
import com.timar.androidstarwars.data.network.PageInfo
import com.timar.androidstarwars.domain.model.BaseModel

interface StarWarsClient {
    suspend fun getCharactersList(pageInfo: PageInfo): BaseDto

    suspend fun getStarShipsList(pageInfo: PageInfo): BaseDto

    suspend fun getPlanetsList(pageInfo: PageInfo): BaseDto

    suspend fun getCharacterDetails(id: String): BaseModel?

    suspend fun getStarShipDetails(id: String): BaseModel?

    suspend fun getPlanetDetails(id: String): BaseModel?
}