package com.tutorlog.app.service.di

import com.tutorlog.app.repository.IUserRepository
import com.tutorlog.app.repository.UserRepository
import com.tutorlog.app.service.UserService
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