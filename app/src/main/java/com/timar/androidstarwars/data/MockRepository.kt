package com.timar.androidstarwars.data

import com.timar.androidstarwars.domain.model.BaseModel
import kotlinx.coroutines.delay

class MockRepository{
    companion object{
        suspend fun getCharacters(): List<BaseModel>{
            delay(3000)
            return mockCharacters
        }

        suspend fun getStarShips(): List<BaseModel>{
            delay(3000)
            return mockStarShips
        }

        suspend fun getPlanets(): List<BaseModel>{
            delay(3000)
            return mockPlanets
        }

        private val mockCharacters: List<BaseModel> = listOf(
            BaseModel("1", "A Skywalker", 5),
            BaseModel("2", "B Skywalker", 6),
            BaseModel("3","C Skywalker", 1),
            BaseModel("4", "D Skywalker", 0),
            BaseModel("5", "E Skywalker", 3),
            BaseModel("6","F Skywalker", 100000),
        )

        private val mockStarShips: List<BaseModel> = listOf(
            BaseModel("1","Millenium Falcon", 5),
            BaseModel("2","Billenium Falcon", 6),
            BaseModel("3","Cillenium Falcon", 1),
            BaseModel("4","Dillenium Falcon", 0),
            BaseModel("5","Eillenium Falcon", 3),
            BaseModel("6", "Fillenium Falcon", 100000),
        )

        private val mockPlanets: List<BaseModel> = listOf(
            BaseModel("1","Tatooine", 5),
            BaseModel("2","Batooine", 6),
            BaseModel("3","Catooine", 1),
            BaseModel("4","Datooine", 0),
            BaseModel("5","Eatooine", 3),
            BaseModel("6","Fatooine", 100000),
        )


    }
}