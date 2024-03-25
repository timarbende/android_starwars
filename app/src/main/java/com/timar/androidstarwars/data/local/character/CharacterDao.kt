package com.timar.androidstarwars.data.local.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
@Dao
interface CharacterDao {

    @Upsert
    suspend fun upsertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM CharacterEntity")
    suspend fun clearAll()
}