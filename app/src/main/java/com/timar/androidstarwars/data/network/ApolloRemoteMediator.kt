@file:OptIn(ExperimentalPagingApi::class)

package com.timar.androidstarwars.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.timar.androidstarwars.data.local.LocalDatabase
import com.timar.androidstarwars.data.transformers.toBaseModelEntity
import com.timar.androidstarwars.domain.network.StarWarsClient

open class ApolloRemoteMediator<T: Any>(
    private val localDatabase: LocalDatabase,
    private val swapi: StarWarsClient
) : RemoteMediator<Int, T>() {

    private var pageInfo: PageInfo? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, T>
    ): MediatorResult {
        var endCursor: String? = null

        if (pageInfo != null) {
            endCursor = when (loadType) {
                LoadType.REFRESH -> {
                    val firstItem = state.firstItemOrNull()
                    if (firstItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    } else {
                        pageInfo!!.endCursor
                    }
                }

                LoadType.PREPEND -> return MediatorResult.Success(
                    endOfPaginationReached = true
                )

                LoadType.APPEND -> {
                    val lastItem = state.lastItemOrNull()
                    if (lastItem == null) {
                        return MediatorResult.Success(
                            endOfPaginationReached = true
                        )
                    } else {
                        pageInfo!!.endCursor
                    }
                }
            }
        }

        val response: BaseDto = swapi.getCharactersList(PageInfo(endCursor = endCursor))

        localDatabase.withTransaction {
            if (loadType == LoadType.REFRESH) {
                localDatabase.characterDao.clearAll()
            }
            val characterEntities = response.data.map { it.toBaseModelEntity() }
            localDatabase.characterDao.upsertCharacters(characterEntities)
        }

        pageInfo = response.pageInfo

        return MediatorResult.Success(
            endOfPaginationReached = response.pageInfo.endCursor == null
        )
    }
}