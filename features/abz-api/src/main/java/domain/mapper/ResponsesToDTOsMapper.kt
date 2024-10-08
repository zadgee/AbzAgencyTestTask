package domain.mapper

import data.responses.EmploymentPositionsResponse
import data.responses.PositionResponse
import data.responses.TokenResponse
import domain.dto.EmploymentPositionsDTO
import domain.dto.PositionDTO
import domain.dto.TokenDTO


fun TokenResponse.toTokenDTO(): TokenDTO {
     return TokenDTO(
         isSuccessful = isSuccessful,
         token = token
     )
 }

fun EmploymentPositionsResponse.toEmploymentPositionsDTO(): EmploymentPositionsDTO {
    return EmploymentPositionsDTO(
        positions = positions.map {
            it.toPositionDTO()
        }
    )
}

fun PositionResponse.toPositionDTO(): PositionDTO {
    return PositionDTO(
        id = id,
        name = name
    )
}