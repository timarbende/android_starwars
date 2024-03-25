package com.timar.androidstarwars.data.transformers

import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.local.planet.PlanetEntity
import com.timar.androidstarwars.data.local.starship.StarShipEntity
import com.timar.androidstarwars.domain.model.BaseModel

fun BaseModel.toCharacterEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
)

fun CharacterEntity.toBaseModel(): BaseModel = BaseModel(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
)

fun BaseModel.toStarShipEntity(): StarShipEntity = StarShipEntity(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
)

fun StarShipEntity.toBaseModel(): BaseModel = BaseModel(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
)

fun BaseModel.toPlanetEntity(): PlanetEntity = PlanetEntity(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
)

fun PlanetEntity.toBaseModel(): BaseModel = BaseModel(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
)