package com.timar.androidstarwars.data.local.starship

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
@Dao
interface StarShipDao {

    @Upsert
    suspend fun upsertStarShips(characters: List<StarShipEntity>)

    @Query("SELECT * FROM StarShipEntity")
    fun pagingSource(): PagingSource<Int, StarShipEntity>

    @Query("DELETE FROM StarShipEntity")
    suspend fun clearAll()
}