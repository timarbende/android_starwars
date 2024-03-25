package com.timar.androidstarwars.data.transformers

import com.timar.AllPlanetsQuery
import com.timar.PlanetQuery
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

fun PlanetQuery.Planet.toBaseModel(): BaseModel = BaseModel(
    id = this.id,
    name = this.name.orEmpty(),
    numberOfFilmReferences = 0,
    details = extractDetails(
        Pair(this.population.toString(), "Population"),
        Pair(this.diameter.toString(), "Diameter"),
        Pair(this.gravity, "Gravity"),
        Pair(this.orbitalPeriod.toString(), "Orbital period"),
        Pair(this.surfaceWater.toString(), "Surface water"),
        Pair(this.rotationPeriod.toString(), "Rotation period"),
        Pair(this.climates?.joinToString(), "Climates")
    )
)