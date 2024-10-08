package domain.repo

import domain.models.TokenStatus

interface SplashScreenRepository {
    suspend fun getJWT(): TokenStatus
    suspend fun saveJWTtoDataStore(token:String)
}