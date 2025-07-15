package com.tm.coreNetwork.model.gameDetails

import kotlinx.serialization.Serializable

@Serializable
data class DeveloperDTO(
    val games_count: Int,
    val id: Int,
    val image_background: String,
    val name: String,
    val slug: String
)