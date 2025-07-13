package com.tm.search.domain.repository

import com.tm.common.domain.model.Game

interface SearchRepository {

    suspend fun search(q: String): Result<List<Game>>
}