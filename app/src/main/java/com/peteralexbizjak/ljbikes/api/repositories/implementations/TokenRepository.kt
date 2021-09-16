package com.peteralexbizjak.ljbikes.api.repositories.implementations

import android.content.SharedPreferences
import com.peteralexbizjak.ljbikes.BuildConfig
import com.peteralexbizjak.ljbikes.api.models.tokens.RefreshTokenRequestModel
import com.peteralexbizjak.ljbikes.api.models.tokens.TokenRequestModel
import com.peteralexbizjak.ljbikes.api.repositories.ITokenRepository
import com.peteralexbizjak.ljbikes.api.services.TokenService
import java.time.Duration
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

internal class TokenRepository(
    private val tokenService: TokenService,
    private val sharedPreferences: SharedPreferences
) : ITokenRepository {
    override suspend fun persistClientTokens() {
        if (sharedPreferences.contains(LAST_UPDATE) && sharedPreferences.contains(REFRESH_TOKEN)) {
            val lastDateTime = LocalDateTime.parse(sharedPreferences.getString(LAST_UPDATE, null))
            val currentDateTime = LocalDateTime.now()
            val difference = Duration.between(lastDateTime, currentDateTime).toHours()
            if (difference > 1L) {
                with(sharedPreferences.edit()) {
                    putString(LAST_UPDATE, currentDateTime.format(DateTimeFormatter.ISO_DATE_TIME))
                    apply()
                }
                refreshAccessToken()
            }

            return
        }

        val requestModel = TokenRequestModel(BuildConfig.API_TOKEN_CODE, BuildConfig.API_TOKEN_KEY)
        val resultModel = tokenService.retrieveClientTokens(requestModel)
        with(sharedPreferences.edit()) {
            putString(REFRESH_TOKEN, resultModel.refreshToken)
            putString(ACCESS_TOKEN, resultModel.token)
            putString(LAST_UPDATE, LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME))
            apply()
        }
    }

    override suspend fun refreshAccessToken() {
        val refreshToken = sharedPreferences.getString(REFRESH_TOKEN, null)
        if (refreshToken != null) {
            val requestModel = RefreshTokenRequestModel(refreshToken)
            val resultModel = tokenService.refreshAccessToken(requestModel)
            with(sharedPreferences.edit()) {
                putString(ACCESS_TOKEN, resultModel.token)
                apply()
            }
        }
    }

    companion object {
        private const val REFRESH_TOKEN = "refresh_token"
        private const val ACCESS_TOKEN = "access_token"
        private const val LAST_UPDATE = "last_update"
    }
}