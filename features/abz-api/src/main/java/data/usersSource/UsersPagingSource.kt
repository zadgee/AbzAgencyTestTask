package data.usersSource
import androidx.paging.PagingSource
import androidx.paging.PagingState
import data.api.AbzAPI
import data.responses.UserModel
import javax.inject.Inject

class UsersPagingSource(
    private val api:AbzAPI
): PagingSource<Int, UserModel>() {
    override fun getRefreshKey(state: PagingState<Int, UserModel>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserModel> {
        return try {
            val currentPage = params.key ?: 1
            val response = api.getUsers(currentPage, params.loadSize)

            if (response.isSuccessful) {
                val users = response.body()?.users ?: emptyList()
                val nextPage = if (users.isNotEmpty()) currentPage + 1 else null

                LoadResult.Page(
                    data = users,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = nextPage
                )
            } else {
                LoadResult.Error(Exception("Error: ${response.code()}"))
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}