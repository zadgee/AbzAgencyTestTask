package com.example.abzagencytesttask.di.impl
import androidx.paging.PagingData
import androidx.paging.map
import com.example.abzagencytesttask.di.mappers.toUserMockModel
import domain.models.UserMockModel
import domain.repo.UsersScreenRepository
import domain.usecases.GetUsersUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersScreenRepositoryImpl @Inject constructor(
    private val getUsersUseCase: GetUsersUseCase
):UsersScreenRepository {
    override fun usersPagingData(): Flow<PagingData<UserMockModel>> {
       return getUsersUseCase.execute().map { pagingData->
           pagingData.map { userModel->
               userModel.toUserMockModel()
           }

       }
    }


}