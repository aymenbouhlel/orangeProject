package com.orangeproject.orangebank.business.useCases

import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.repository.OrangeRepository
import com.orangeproject.utils.ResponseHTTP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GetAllAccountUseCase @Inject constructor(private val repository: OrangeRepository) {

    suspend operator fun invoke(): Flow<ResponseHTTP<List<OrangeAccount>>> = channelFlow {
        try {
            send(ResponseHTTP.Loading())
            val account = repository.getAllAccount()
            send(ResponseHTTP.Success(account))

        }catch (e: Exception) {
            send(ResponseHTTP.Error(e.localizedMessage?:e.message))
        }
    }

}



