package com.tm.search.data.repository

import com.tm.common.data.mappers.toDomainListOfGames
import com.tm.common.domain.model.Game
import com.tm.coreNetwork.apiService.ApiService
import com.tm.search.domain.repository.SearchRepository

class SearchRepositoryImpl(private val apiService: ApiService) : SearchRepository {

    override suspend fun search(q: String): Result<List<Game>> {
        return try {
            val response = apiService.getSearch(q)
            val data = response.getOrThrow().results.toDomainListOfGames()
            Result.success(data)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}