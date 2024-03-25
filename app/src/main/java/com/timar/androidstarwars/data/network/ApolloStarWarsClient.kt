package com.timar.androidstarwars.data.network

import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.timar.AllCharactersQuery
import com.timar.AllPlanetsQuery
import com.timar.AllStarShipsQuery
import com.timar.PersonQuery
import com.timar.PlanetQuery
import com.timar.StarshipQuery
import com.timar.androidstarwars.data.transformers.toBaseModel
import com.timar.androidstarwars.data.transformers.toBaseModelList
import com.timar.androidstarwars.data.transformers.toPageInfo
import com.timar.androidstarwars.di.pageSize
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.domain.network.StarWarsClient

class ApolloStarWarsClient(
    private val apolloClient: ApolloClient
) : StarWarsClient {
    override suspend fun getCharactersList(pageInfo: PageInfo): BaseDto {
        val result =  apolloClient
            .query(AllCharactersQuery(
                after = if(pageInfo.endCursor != null) Optional.Present(pageInfo.endCursor)
                        else Optional.absent(),
                first = Optional.Present(pageSize),
            ))
            .execute()
            .data
            ?.allPeople

        return BaseDto(
            pageInfo = result?.pageInfo?.toPageInfo() ?: PageInfo(),
            data = result?.toBaseModelList().orEmpty()
        )
    }

    override suspend fun getStarShipsList(pageInfo: PageInfo): BaseDto {
        val result =  apolloClient
            .query(AllStarShipsQuery(
                after = if(pageInfo.endCursor != null) Optional.Present(pageInfo.endCursor)
                else Optional.absent(),
                first = Optional.Present(pageSize),
            ))
            .execute()
            .data
            ?.allStarships

        return BaseDto(
            pageInfo = result?.pageInfo?.toPageInfo() ?: PageInfo(),
            data = result?.toBaseModelList().orEmpty()
        )
    }

    override suspend fun getPlanetsList(pageInfo: PageInfo): BaseDto {
        val result =  apolloClient
            .query(AllPlanetsQuery(
                after = if(pageInfo.endCursor != null) Optional.Present(pageInfo.endCursor)
                else Optional.absent(),
                first = Optional.Present(pageSize),
            ))
            .execute()
            .data
            ?.allPlanets

        return BaseDto(
            pageInfo = result?.pageInfo?.toPageInfo() ?: PageInfo(),
            data = result?.toBaseModelList().orEmpty()
        )
    }

    override suspend fun getCharacterDetails(id: String): BaseModel? {
        return apolloClient
            .query(PersonQuery(Optional.Present(id)))
            .execute()
            .data
            ?.person
            ?.toBaseModel()
    }

    override suspend fun getStarShipDetails(id: String): BaseModel? {
        return apolloClient
            .query(StarshipQuery(Optional.Present(id)))
            .execute()
            .data
            ?.starship
            ?.toBaseModel()
    }

    override suspend fun getPlanetDetails(id: String): BaseModel? {
        return apolloClient
            .query(PlanetQuery(Optional.Present(id)))
            .execute()
            .data
            ?.planet
            ?.toBaseModel()
    }
}