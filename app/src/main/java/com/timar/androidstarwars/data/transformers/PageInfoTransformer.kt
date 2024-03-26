package com.timar.androidstarwars.data.transformers

import com.timar.AllCharactersQuery
import com.timar.AllPlanetsQuery
import com.timar.AllStarShipsQuery
import com.timar.androidstarwars.data.network.PageInfo

fun AllCharactersQuery.PageInfo.toPageInfo() : PageInfo = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
)

fun AllStarShipsQuery.PageInfo.toPageInfo() : PageInfo = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
)

fun AllPlanetsQuery.PageInfo.toPageInfo() : PageInfo = PageInfo(
    startCursor = startCursor,
    endCursor = endCursor,
)