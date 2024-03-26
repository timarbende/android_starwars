@file:OptIn(ExperimentalPagingApi::class)

package com.timar.androidstarwars.data.network

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.timar.androidstarwars.data.local.LocalDatabase
import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.transformers.toCharacterEntity
import com.timar.androidstarwars.domain.network.StarWarsClient

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
                    null
                }
                pageInfo?.endCursor
            }
        }

        val response: BaseDto = swapi.getCharactersList(PageInfo(endCursor = endCursor))
        if(response.data.isEmpty() && response.pageInfo.endCursor == null && response.pageInfo.startCursor == null){
            return MediatorResult.Error(Exception("Star Wars API unaccessible"))
        }

        localDatabase.withTransaction {
            var oldCharacterEntities: List<CharacterEntity> = emptyList()
            if (loadType == LoadType.REFRESH) {
                oldCharacterEntities = localDatabase.characterDao.getAll()
                localDatabase.characterDao.clearAll()
            }
            val newCharacterEntities = response.data.map { it.toCharacterEntity() }
            localDatabase.characterDao.upsertCharacters(newCharacterEntities)
            oldCharacterEntities.forEach {entity->
                localDatabase.characterDao.updateCharacterFavourite(entity.id, entity.isFavourite)
            }
        }

        pageInfo = response.pageInfo

        return MediatorResult.Success(
            endOfPaginationReached = response.data.isEmpty()
        )
    }
}