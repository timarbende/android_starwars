package com.timar.androidstarwars.data.transformers

import com.timar.AllCharactersQuery
import com.timar.PersonQuery
import com.timar.androidstarwars.domain.model.BaseModel
import com.timar.androidstarwars.domain.model.Detail

fun AllCharactersQuery.AllPeople.toBaseModelList(): List<BaseModel> =
    this.people?.mapNotNull { person ->
        BaseModel(
            id = person?.id.orEmpty(),
            name = person?.name.orEmpty(),
            numberOfFilmReferences = person?.filmConnection?.totalCount ?: 0
        )
    }.orEmpty()

fun PersonQuery.Person.toBaseModel(): BaseModel = BaseModel(
    id = this.id,
    name = this.name.orEmpty(),
    numberOfFilmReferences = 0,
    details = extractDetails(
        Pair(this.birthYear, "Year of birth"),
        Pair(this.gender, "Gender"),
        Pair(this.eyeColor, "Eye color"),
        Pair(this.hairColor, "Hair color"),
        Pair(this.height?.toString(), "Height"),
        Pair(this.mass?.toString(), "Mass")
    )
)