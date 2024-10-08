package domain.usecases

import androidx.paging.PagingData
import data.responses.UserModel
import domain.repo.AbzRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetUsersUseCase @Inject constructor(
    private val repository: AbzRepository
) {
    fun execute(): Flow<PagingData<UserModel>> = repository.usersPagingSource().flow
}