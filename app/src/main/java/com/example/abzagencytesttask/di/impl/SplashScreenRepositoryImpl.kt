package com.example.abzagencytesttask.di.impl

import com.example.abzagencytesttask.di.mappers.toTokenStatus
import domain.models.TokenStatus
import domain.repo.SplashScreenRepository
import domain.usecases.GetTokenUseCase
import manager.DataStoreManager
import javax.inject.Inject

class SplashScreenRepositoryImpl @Inject constructor(
    private val getTokenUseCase: GetTokenUseCase,
    private val dataStoreManager: DataStoreManager
):SplashScreenRepository {
    override suspend fun getJWT(): TokenStatus {
        return getTokenUseCase.execute().toTokenStatus()
    }

    override suspend fun saveJWTtoDataStore(token: String) {
         dataStoreManager.saveToken(token)
    }
}