package domain.usecases

import domain.dto.TokenDTO
import domain.repo.AbzRepository
import javax.inject.Inject

class GetTokenUseCase @Inject constructor(
    private val repository: AbzRepository
) {

    suspend fun execute():TokenDTO{
        return repository.getToken()
    }
}