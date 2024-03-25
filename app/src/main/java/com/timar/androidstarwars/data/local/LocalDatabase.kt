package com.timar.androidstarwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timar.androidstarwars.data.local.character.CharacterDao
import com.timar.androidstarwars.data.local.character.CharacterEntity

@Database(
    entities = [
        CharacterEntity::class,
    ],
    version = 1
)
abstract class LocalDatabase: RoomDatabase() {
    abstract val characterDao: CharacterDao
}