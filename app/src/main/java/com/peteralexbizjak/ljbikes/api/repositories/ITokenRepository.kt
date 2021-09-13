package com.peteralexbizjak.ljbikes.api.repositories

import com.peteralexbizjak.ljbikes.api.models.tokens.TokenResultModel

internal interface ITokenRepository {
    suspend fun persistClientTokens()
}