package com.timar.androidstarwars.data.transformers

import com.timar.AllPlanetsQuery
import com.timar.androidstarwars.domain.model.BaseModel

fun AllPlanetsQuery.AllPlanets.toBaseModelList(): List<BaseModel> {
    return this.planets?.mapNotNull {planet->
        BaseModel(
            id = planet?.id.orEmpty(),
            name = planet?.name.orEmpty(),
            numberOfFilmReferences = planet?.filmConnection?.totalCount ?: 0
        )
    }.orEmpty()
}