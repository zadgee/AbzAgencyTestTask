package domain.usecases

import domain.dto.EmploymentPositionsDTO
import domain.repo.AbzRepository
import javax.inject.Inject

class GetEmploymentPositionsUseCase @Inject constructor(
    private val repository: AbzRepository
) {

    suspend fun execute(): EmploymentPositionsDTO?{
        return repository.getEmploymentPositions()
    }

}