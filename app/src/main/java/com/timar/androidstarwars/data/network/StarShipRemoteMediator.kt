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

        val response: BaseDto = swapi.getStarShipsList(PageInfo(endCursor = endCursor))
        if(response.data.isEmpty() && response.pageInfo.endCursor == null && response.pageInfo.startCursor == null){
            return MediatorResult.Error(Exception("Star Wars API unaccessible"))
        }

        localDatabase.withTransaction {
            if (loadType == LoadType.REFRESH) {
                localDatabase.starShipDao.clearAll()
            }
            val starshipEntities = response.data.map { it.toStarShipEntity() }
            localDatabase.starShipDao.upsertStarShips(starshipEntities)
        }

        pageInfo = response.pageInfo

        return MediatorResult.Success(
            endOfPaginationReached = response.data.isEmpty()
        )
    }
}