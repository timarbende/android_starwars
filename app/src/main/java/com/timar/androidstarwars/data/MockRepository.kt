package com.timar.androidstarwars.data

import com.timar.androidstarwars.domain.model.BaseModel
import kotlinx.coroutines.delay

class MockRepository{
    companion object{
        suspend fun getCharacters(): List<BaseModel>{
            delay(3000)
            return mockCharacters
        }

        private val mockCharacters: List<BaseModel> = listOf(
            BaseModel("A Skywalker", 5),
            BaseModel("B Skywalker", 6),
            BaseModel("C Skywalker", 1),
            BaseModel("D Skywalker", 0),
            BaseModel("E Skywalker", 3),
            BaseModel("F Skywalker", 100000),
        )
    }
}