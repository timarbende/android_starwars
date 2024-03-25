package com.timar.androidstarwars.data.transformers

import com.timar.AllCharactersQuery
import com.timar.androidstarwars.data.network.PageInfo

fun AllCharactersQuery.PageInfo.toPageInfo() : PageInfo = PageInfo(
    endCursor = endCursor,
)