package com.timar.androidstarwars.data.local.planet

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
@Dao
interface PlanetDao {

    @Upsert
    suspend fun upsertPlanets(characters: List<PlanetEntity>)

    @Query("SELECT * FROM PlanetEntity")
    fun pagingSource(): PagingSource<Int, PlanetEntity>

    @Query("DELETE FROM PlanetEntity")
    suspend fun clearAll()
}