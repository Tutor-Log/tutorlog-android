package com.example.tutorlog.domain.usecase

import com.example.tutorlog.domain.local_storage.PreferencesManager
import com.example.tutorlog.repository.IUserRepository
import javax.inject.Inject

class RGetGroupInfoUseCase @Inject constructor(
    private val userRepository: IUserRepository,
    private val preferencesManager: PreferencesManager
) {

//    suspend fun process{
//
//    }
//
//
//    data class UCRequest(
//        val groupId: Int
//    )
//
//    data class UCResponse(
//        val groupName: String
//    )
}