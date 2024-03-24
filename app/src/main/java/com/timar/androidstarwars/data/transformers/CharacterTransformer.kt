package com.timar.androidstarwars.data.transformers

import com.timar.AllCharactersQuery
import com.timar.androidstarwars.domain.model.BaseModel

fun AllCharactersQuery.AllPeople.toBaseModelList(): List<BaseModel> =
    this.people?.mapNotNull { person ->
        BaseModel(
            name = person?.name.orEmpty(),
            numberOfFilmReferences = person?.filmConnection?.totalCount ?: 0
        )
    }.orEmpty()