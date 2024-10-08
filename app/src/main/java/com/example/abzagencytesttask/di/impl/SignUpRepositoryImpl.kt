package com.example.abzagencytesttask.di.impl

import com.example.abzagencytesttask.di.mappers.signUpEventMapper
import com.example.abzagencytesttask.di.mappers.toEmploymentPositionsMock
import domain.models.EmploymentPositionsMock
import domain.models.SignUpEvent
import domain.repo.SignUpRepository
import domain.usecases.GetEmploymentPositionsUseCase
import domain.usecases.SignUpUseCase
import manager.DataStoreManager
import java.io.File
import javax.inject.Inject

class SignUpRepositoryImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
    private val signUpUseCase: SignUpUseCase,
    private val getPositionsUseCase: GetEmploymentPositionsUseCase
):SignUpRepository {
    override suspend fun getJWT(): String {
        return dataStoreManager.getToken()?:throw Exception("JWT not found")
    }

    override suspend fun signUp(
        token: String,
        name: String,
        email: String,
        phone: String,
        positionId: Int,
        photo: File
    ): SignUpEvent {
        val signUpRequestHandler = signUpUseCase.execute(
            token = token,
            name = name,
            email = email,
            phone = phone,
            positionId = positionId,
            photo = photo
        )
        return signUpEventMapper(signUpRequestHandler)
    }

    override suspend fun getPositions(): EmploymentPositionsMock {
        return getPositionsUseCase.execute()?.toEmploymentPositionsMock() ?: EmploymentPositionsMock(
            emptyList()
        )
    }
}