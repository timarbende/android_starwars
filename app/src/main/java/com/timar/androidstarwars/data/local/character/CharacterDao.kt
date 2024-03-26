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

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCharacters(characters: List<CharacterEntity>)

    @Query("UPDATE CharacterEntity SET name = :name, numberOfFilmReferences = :numberOfFilmReferences WHERE id = :id")
    suspend fun updateCharacter(id: String, name: String, numberOfFilmReferences: Int)

    @Query("SELECT * FROM CharacterEntity")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM CharacterEntity")
    suspend fun clearAll()

    @Upsert
    suspend fun upsertCharacter(character: CharacterEntity)
}