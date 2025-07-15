package com.tm.coreNetwork.apiService

import com.tm.coreNetwork.model.game.GameResponse
import com.tm.coreNetwork.model.gameDetails.GameDetailsResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(val httpClient: HttpClient) {

    suspend fun getGames(): Result<GameResponse> {
        return try {
            val response = httpClient.get("api/games") {
                url {
                    parameter("key", "a8e6e41489bd42beb2b136335262bc68")
                }
            }.body<GameResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getSearch(q: String): Result<GameResponse> {
        return try {
            val response = httpClient.get("api/games") {
                url {
                    parameter("key", "a8e6e41489bd42beb2b136335262bc68")
                    parameter("search", q)
                }
            }.body<GameResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getDetails(id: Int): Result<GameDetailsResponse> {
        return try {
            val response = httpClient.get("api/games/${id}") {
                url {
                    parameter("key", "a8e6e41489bd42beb2b136335262bc68")
                }
            }.body<GameDetailsResponse>()
            Result.success(response)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}