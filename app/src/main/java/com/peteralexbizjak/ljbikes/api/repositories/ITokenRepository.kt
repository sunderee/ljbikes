package com.peteralexbizjak.ljbikes.api.repositories

internal interface ITokenRepository {
    suspend fun persistClientTokens()
    suspend fun refreshAccessToken()
}