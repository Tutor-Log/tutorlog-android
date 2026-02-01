package com.tutorlog.app.domain.usecase

import com.tutorlog.app.domain.local_storage.PreferencesManager
import com.tutorlog.app.repository.IUserRepository
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