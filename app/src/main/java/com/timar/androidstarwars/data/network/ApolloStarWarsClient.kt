package com.timar.androidstarwars.data.network

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.timar.AllCharactersQuery
import com.timar.AllPlanetsQuery
import com.timar.AllStarShipsQuery
import com.timar.PersonQuery
import com.timar.androidstarwars.data.transformers.toBaseModel
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
        return apolloClient
            .query(AllStarShipsQuery())
            .execute()
            .data
            ?.allStarships
            ?.toBaseModelList()
            .orEmpty()
    }

    override suspend fun getPlanetsList(): List<BaseModel> {
        return apolloClient
            .query(AllPlanetsQuery())
            .execute()
            .data
            ?.allPlanets
            ?.toBaseModelList()
            .orEmpty()
    }

    override suspend fun getCharacterDetails(id: String): BaseModel? {
        return apolloClient
            .query(PersonQuery(Optional.Present(id)))
            .execute()
            .data
            ?.person
            ?.toBaseModel()
    }
}