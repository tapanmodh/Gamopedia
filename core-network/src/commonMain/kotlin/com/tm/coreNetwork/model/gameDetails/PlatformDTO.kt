package com.tm.coreNetwork.model.gameDetails

import kotlinx.serialization.Serializable

@Serializable
data class PlatformDTO(
    val id: Int,
    val name: String,
    val slug: String
)