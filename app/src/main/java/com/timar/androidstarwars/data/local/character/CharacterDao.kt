package com.timar.androidstarwars.data.local.character

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
@Dao
interface CharacterDao {

    @Upsert
    suspend fun upsertCharacters(characters: List<CharacterEntity>)

    @Query("SELECT * FROM CharacterEntity")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("SELECT * FROM CharacterEntity")
    fun getAll(): List<CharacterEntity>

    @Query("DELETE FROM CharacterEntity")
    suspend fun clearAll()

    @Query("UPDATE CharacterEntity SET isFavourite = :isFavourite WHERE id = :id")
    suspend fun updateCharacterFavourite(id: String, isFavourite: Boolean)
}