package com.timar.androidstarwars.data.transformers

import com.timar.AllStarShipsQuery
import com.timar.androidstarwars.domain.model.BaseModel

fun AllStarShipsQuery.AllStarships.toBaseModelList(): List<BaseModel> {
    return this.starships?.mapNotNull {starShip ->
        BaseModel(
            id = starShip?.id.orEmpty(),
            name = starShip?.name.orEmpty(),
            numberOfFilmReferences = starShip?.filmConnection?.totalCount ?: 0
        )
    }.orEmpty()
}