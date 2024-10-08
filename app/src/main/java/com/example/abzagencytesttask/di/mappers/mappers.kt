package com.example.abzagencytesttask.di.mappers

import data.responses.UserModel
import domain.dto.EmploymentPositionsDTO
import domain.dto.PositionDTO
import domain.dto.TokenDTO
import domain.models.EmploymentPositionMock
import domain.models.EmploymentPositionsMock
import domain.models.SignUpEvent
import domain.models.TokenStatus
import domain.models.UserMockModel
import domain.requestHandler.SignUpRequestHandler

fun TokenDTO.toTokenStatus(): TokenStatus {
    return TokenStatus(
        success = isSuccessful,
        token = token
    )
}

fun UserModel.toUserMockModel(): UserMockModel {
    return UserMockModel(
        name = name,
        email = email,
        phone = phone,
        photo = photo,
        position = position
    )
}

fun EmploymentPositionsDTO.toEmploymentPositionsMock():EmploymentPositionsMock{
    return EmploymentPositionsMock(
        positions = positions.map {
            it.toPositionMock()
        }
    )
}

fun PositionDTO.toPositionMock(): EmploymentPositionMock {
    return EmploymentPositionMock(
        id = id,
        name = name
    )
}

fun signUpEventMapper(signUpRequestHandler: SignUpRequestHandler):SignUpEvent{
   return when(signUpRequestHandler){
        is SignUpRequestHandler.Error -> SignUpEvent.Error(signUpRequestHandler.errorMessage)
        is SignUpRequestHandler.Success -> SignUpEvent.Success(signUpRequestHandler.successMessage)
    }
}