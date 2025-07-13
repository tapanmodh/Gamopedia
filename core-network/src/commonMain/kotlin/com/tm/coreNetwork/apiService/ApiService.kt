package com.tm.coreNetwork.apiService

import com.tm.coreNetwork.model.game.GameResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.http.parameters

class ApiService (val httpClient: HttpClient) {

    suspend fun getGames(): Result<GameResponse> {
        return try {
         val response = httpClient.get("api/games") {
             url {
                 parameter("key", "35a5cce3dea54c57abb137bac3b2c65b")
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
                    parameter("key", "35a5cce3dea54c57abb137bac3b2c65b")
                    parameter("search", q)
                }
            }.body<GameResponse>()
            Result.success(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}