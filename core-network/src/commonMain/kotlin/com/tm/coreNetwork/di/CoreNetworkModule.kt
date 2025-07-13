package com.tm.coreNetwork.di

import com.tm.coreNetwork.apiService.ApiService
import com.tm.coreNetwork.client.KtorClient
import org.koin.dsl.module

fun getCoreNetworkModule() = module {
    single { ApiService(httpClient = KtorClient.getInstance()) }
}