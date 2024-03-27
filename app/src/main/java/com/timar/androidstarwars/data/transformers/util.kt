package com.timar.androidstarwars.data.transformers

import com.timar.androidstarwars.domain.model.Detail

fun extractDetails(vararg data: Pair<String?, String>): List<Detail> =
    data.mapNotNull {
        if (it.first != null) {
            Detail(
                it.first!!,
                it.second
            )
        } else null
    }