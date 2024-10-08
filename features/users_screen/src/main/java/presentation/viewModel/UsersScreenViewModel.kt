package presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.models.UserMockModel
import domain.repo.UsersScreenRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import javax.inject.Inject

@HiltViewModel
class UsersScreenViewModel @Inject constructor(
    repository: UsersScreenRepository
):ViewModel() {

    val pagingData:Flow<PagingData<UserMockModel>> = repository.usersPagingData()
        .distinctUntilChanged()
        .cachedIn(viewModelScope)


}