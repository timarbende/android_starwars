package com.timar.androidstarwars.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.timar.androidstarwars.data.local.character.CharacterDao
import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.local.planet.PlanetDao
import com.timar.androidstarwars.data.local.planet.PlanetEntity
import com.timar.androidstarwars.data.local.starship.StarShipDao
import com.timar.androidstarwars.data.local.starship.StarShipEntity

@Database(
    entities = [
        CharacterEntity::class,
        StarShipEntity::class,
        PlanetEntity::class
    ],
    version = 1
)
abstract class LocalDatabase: RoomDatabase() {
    abstract val characterDao: CharacterDao
    abstract val starShipDao: StarShipDao
    abstract val planetDao: PlanetDao
}