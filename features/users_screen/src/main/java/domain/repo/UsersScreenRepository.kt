package domain.repo
import androidx.paging.PagingData
import domain.models.UserMockModel
import kotlinx.coroutines.flow.Flow

interface UsersScreenRepository {
    fun usersPagingData():Flow<PagingData<UserMockModel>>
}