package com.peteralexbizjak.ljbikes.api.services

import com.peteralexbizjak.ljbikes.api.models.tokens.TokenRequestModel
import com.peteralexbizjak.ljbikes.api.models.tokens.TokenResultModel
import retrofit2.http.Body
import retrofit2.http.POST

internal interface TokenService {
    @POST("auth/environments/PRD/client_tokens")
    suspend fun retrieveClientTokens(@Body requestModel: TokenRequestModel): TokenResultModel
}