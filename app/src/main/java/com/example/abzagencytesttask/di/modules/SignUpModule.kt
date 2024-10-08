package com.example.abzagencytesttask.di.modules

import android.content.Context
import com.example.abzagencytesttask.di.impl.SignUpRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import domain.repo.SignUpRepository
import domain.usecases.ConvertFileToUriUseCase
import domain.usecases.GetEmploymentPositionsUseCase
import domain.usecases.SignUpUseCase
import manager.DataStoreManager

@Module
@InstallIn(ViewModelComponent::class)
class SignUpModule {

    @Provides
    @ViewModelScoped
    fun provideSignUpRepository(
        dataStoreManager: DataStoreManager,
        signUpUseCase: SignUpUseCase,
        getEmploymentPositionsUseCase: GetEmploymentPositionsUseCase
    ): SignUpRepository {
        return SignUpRepositoryImpl(
            signUpUseCase = signUpUseCase,
            dataStoreManager = dataStoreManager,
            getPositionsUseCase = getEmploymentPositionsUseCase
        )
    }

    @Provides
    @ViewModelScoped
    fun provideConvertFileToUriUseCase(
        @ApplicationContext context:Context
    ):ConvertFileToUriUseCase{
     return ConvertFileToUriUseCase(
         context = context
     )
    }

}