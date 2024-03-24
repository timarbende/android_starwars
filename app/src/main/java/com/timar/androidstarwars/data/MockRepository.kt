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

        private val mockCharacters: List<BaseModel> = listOf(
            BaseModel("A Skywalker", 5),
            BaseModel("B Skywalker", 6),
            BaseModel("C Skywalker", 1),
            BaseModel("D Skywalker", 0),
            BaseModel("E Skywalker", 3),
            BaseModel("F Skywalker", 100000),
        )

        private val mockStarShips: List<BaseModel> = listOf(
            BaseModel("Millenium Falcon", 5),
            BaseModel("Billenium Falcon", 6),
            BaseModel("Cillenium Falcon", 1),
            BaseModel("Dillenium Falcon", 0),
            BaseModel("Eillenium Falcon", 3),
            BaseModel("Fillenium Falcon", 100000),
        )
    }
}