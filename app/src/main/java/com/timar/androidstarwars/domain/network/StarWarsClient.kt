package com.timar.androidstarwars.domain.network

import com.timar.androidstarwars.domain.model.BaseModel

interface StarWarsClient {
    suspend fun getCharactersList(): List<BaseModel>

    suspend fun getStarShipsList(): List<BaseModel>

    suspend fun getPlanetsList(): List<BaseModel>
}