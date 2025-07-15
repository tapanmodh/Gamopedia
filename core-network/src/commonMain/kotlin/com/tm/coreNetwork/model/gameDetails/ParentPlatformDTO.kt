package com.tm.coreNetwork.model.gameDetails

import kotlinx.serialization.Serializable

@Serializable
data class ParentPlatformDTO(
    val platform: PlatformDTO
)