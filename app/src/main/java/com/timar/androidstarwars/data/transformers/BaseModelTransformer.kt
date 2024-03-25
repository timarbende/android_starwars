package com.timar.androidstarwars.data.transformers

import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.domain.model.BaseModel

fun BaseModel.toBaseModelEntity(): CharacterEntity = CharacterEntity(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
)

fun CharacterEntity.toBaseModel(): BaseModel = BaseModel(
    id = id,
    name = name,
    numberOfFilmReferences = numberOfFilmReferences,
    //details = details
)