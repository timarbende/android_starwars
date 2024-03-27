package com.timar.androidstarwars.data.transformers

import com.timar.AllStarShipsQuery
import com.timar.StarshipQuery
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

fun StarshipQuery.Starship.toBaseModel(): BaseModel = BaseModel(
    id = this.id,
    name = this.name.orEmpty(),
    numberOfFilmReferences = 0,
    details = extractDetails(
        Pair(this.starshipClass.toString(), "Class"),
        Pair(this.crew, "Crew"),
        Pair(this.maxAtmospheringSpeed.toString(), "Max speed"),
        Pair(this.manufacturers?.joinToString(), "Manufacturers"),
        Pair(this.costInCredits.toString(), "Cost (credits)"),
        Pair(this.cargoCapacity.toString(), "Cargo capacity"),
        Pair(this.consumables, "Consumables"),
    )
)