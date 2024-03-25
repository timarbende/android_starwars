@file:OptIn(ExperimentalPagingApi::class)

package com.timar.androidstarwars.data.network

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.timar.androidstarwars.data.local.LocalDatabase
import com.timar.androidstarwars.data.local.starship.StarShipEntity
import com.timar.androidstarwars.data.transformers.toStarShipEntity
import com.timar.androidstarwars.domain.network.StarWarsClient

class StarShipRemoteMediator(
    private val localDatabase: LocalDatabase,
    private val swapi: StarWarsClient
) : RemoteMediator<Int, StarShipEntity>() {

    private var pageInfo: PageInfo? = null

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, StarShipEntity>
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

        val response: BaseDto = swapi.getStarShipsList(PageInfo(endCursor = endCursor))

        localDatabase.withTransaction {
            if (loadType == LoadType.REFRESH) {
                localDatabase.starShipDao.clearAll()
            }
            val starshipEntities = response.data.map { it.toStarShipEntity() }
            localDatabase.starShipDao.upsertStarShips(starshipEntities)
        }

        pageInfo = response.pageInfo

        return MediatorResult.Success(
            endOfPaginationReached = response.pageInfo.endCursor == null
        )
    }
}