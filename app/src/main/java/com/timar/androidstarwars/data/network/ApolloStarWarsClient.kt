package com.timar.androidstarwars.data.network

import com.apollographql.apollo3.ApolloClient
import com.timar.AllCharactersQuery
import com.timar.androidstarwars.data.transformers.toBaseModelList
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.domain.network.StarWarsClient

class ApolloStarWarsClient(
    private val apolloClient: ApolloClient
) : StarWarsClient {
    override suspend fun getCharactersList(): List<BaseModel> {
        return apolloClient
            .query(AllCharactersQuery())
            .execute()
            .data
            ?.allPeople
            ?.toBaseModelList()
            .orEmpty()
    }

    override suspend fun getStarShipsList(): List<BaseModel> {
        TODO("Not yet implemented")
    }

    override suspend fun getPlanetsList(): List<BaseModel> {
        TODO("Not yet implemented")
    }
}