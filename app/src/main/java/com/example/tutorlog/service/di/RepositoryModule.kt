package com.example.tutorlog.service.di

import com.example.tutorlog.repository.IUserRepository
import com.example.tutorlog.repository.UserRepository
import com.example.tutorlog.service.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        apiService: UserService
    ): IUserRepository {
        return UserRepository(apiService)
    }
}