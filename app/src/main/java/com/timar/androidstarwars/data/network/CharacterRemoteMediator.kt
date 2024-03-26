@file:OptIn(ExperimentalPagingApi::class)

package com.timar.androidstarwars.data.network

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.apollographql.apollo3.exception.ApolloException
import com.timar.androidstarwars.data.local.LocalDatabase
import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.transformers.toCharacterEntity
import com.timar.androidstarwars.domain.network.StarWarsClient
import java.io.IOException

class CharacterRemoteMediator(
    private val localDatabase: LocalDatabase,
    private val swapi: StarWarsClient
) : RemoteMediator<Int, CharacterEntity>() {

    private var pageInfo: PageInfo? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>
    ): MediatorResult {
        val endCursor = when (loadType) {
            LoadType.REFRESH -> null
            LoadType.PREPEND -> return MediatorResult.Success(
                endOfPaginationReached = true
            )

            LoadType.APPEND -> {
                val lastItem = state.lastItemOrNull()
                if (lastItem == null) {
                    return MediatorResult.Success(
                        endOfPaginationReached = true
                    )
                }
                pageInfo?.endCursor
            }
        }

        val response: BaseDto = swapi.getCharactersList(PageInfo(endCursor = endCursor))
        if(response.data.isEmpty() && response.pageInfo.endCursor == null && response.pageInfo.startCursor == null){
            return MediatorResult.Error(Exception("Star Wars API unaccessible"))
        }

        localDatabase.withTransaction {
            Log.d("CharacterRemoteMediator", "loadType: $loadType")
            if (loadType == LoadType.REFRESH) {
                localDatabase.characterDao.clearAll()
            }
            val characterEntities = response.data.map { it.toCharacterEntity() }
            characterEntities.forEach {
                localDatabase.characterDao.updateCharacter(
                    it.id,
                    it.name,
                    it.numberOfFilmReferences
                )
            }
            localDatabase.characterDao.insertCharacters(characterEntities)
        }

        pageInfo = response.pageInfo

        return MediatorResult.Success(
            endOfPaginationReached = response.data.isEmpty()
        )
    }
}