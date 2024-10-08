package com.example.abzagencytesttask.di.modules

import com.example.abzagencytesttask.di.impl.SplashScreenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import domain.repo.SplashScreenRepository
import domain.usecases.GetTokenUseCase
import manager.DataStoreManager

@Module
@InstallIn(ViewModelComponent::class)
class SplashModule {

    @Provides
    @ViewModelScoped
    fun provideSplashScreenRepository(
        getTokenUseCase: GetTokenUseCase,
        dataStoreManager: DataStoreManager
    ): SplashScreenRepository {
        return SplashScreenRepositoryImpl(
            getTokenUseCase = getTokenUseCase,
            dataStoreManager = dataStoreManager
        )
    }

}