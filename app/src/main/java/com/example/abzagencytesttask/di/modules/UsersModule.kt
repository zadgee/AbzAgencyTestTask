package com.example.abzagencytesttask.di.modules

import com.example.abzagencytesttask.di.impl.UsersScreenRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import domain.repo.UsersScreenRepository
import domain.usecases.GetUsersUseCase

@Module
@InstallIn(ViewModelComponent::class)
class UsersModule {

    @Provides
    @ViewModelScoped
    fun provideUsersScreenRepository(
        getUsersUseCase: GetUsersUseCase
    ): UsersScreenRepository {
        return UsersScreenRepositoryImpl(
            getUsersUseCase = getUsersUseCase
        )
    }

}