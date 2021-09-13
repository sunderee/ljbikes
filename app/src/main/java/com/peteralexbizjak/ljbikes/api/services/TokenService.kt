package com.peteralexbizjak.ljbikes.api.services

import com.peteralexbizjak.ljbikes.api.models.tokens.TokenRequestModel
import com.peteralexbizjak.ljbikes.api.models.tokens.TokenResultModel
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

internal interface TokenService {
    @Headers("host: api.cyclocity.fr", "user-agent: okhttp/4.9.0 vls.android.ljubljana:PRD/1.19.2")
    @POST("auth/environments/PRD/client_tokens")
    suspend fun retrieveClientTokens(@Body requestModel: TokenRequestModel): TokenResultModel
}