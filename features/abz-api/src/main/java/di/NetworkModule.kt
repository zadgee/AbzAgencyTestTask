package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import data.api.AbzAPI
import data.impl.AbzRepositoryImpl
import domain.repo.AbzRepository
import domain.usecases.GetEmploymentPositionsUseCase
import domain.usecases.GetTokenUseCase
import domain.usecases.GetUsersUseCase
import domain.usecases.SignUpUseCase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideInterceptor():HttpLoggingInterceptor{
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }


    @Provides
    @Singleton
    fun provideOkhttp3Client(
        interceptor:HttpLoggingInterceptor
    ):OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofitClient(
      okHttpClient:OkHttpClient,
    ):Retrofit{
        return Retrofit.Builder()
            .baseUrl("https://frontend-test-assignment-api.abz.agency/api/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(
        retrofit: Retrofit
    ):AbzAPI{
        return retrofit.create(AbzAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideAbzRepository(
        abzAPI: AbzAPI
    ):AbzRepository{
        return AbzRepositoryImpl(
            abzAPI
        )
    }

    @Provides
    @Singleton
    fun provideGetTokenUseCase(
        abzRepository: AbzRepository
    ):GetTokenUseCase{
        return GetTokenUseCase(
            abzRepository
        )
    }

    @Provides
    @Singleton
    fun provideSignUpUseCase(
        abzRepository: AbzRepository
    ):SignUpUseCase{
        return SignUpUseCase(
            abzRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetEmploymentPositionsUseCase(
        abzRepository: AbzRepository
    ):GetEmploymentPositionsUseCase{
        return GetEmploymentPositionsUseCase(
            abzRepository
        )
    }

    @Provides
    @Singleton
    fun provideGetUsersUseCase(
        abzRepository: AbzRepository
    ):GetUsersUseCase{
        return GetUsersUseCase(
            abzRepository
        )
    }

}