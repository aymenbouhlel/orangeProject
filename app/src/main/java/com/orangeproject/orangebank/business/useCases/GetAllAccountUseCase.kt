package com.orangeproject.orangebank.business.useCases

import com.orangeproject.orangebank.business.repository.Repository
import com.orangeproject.orangebank.repository.models.account.AccountResponse
import com.orangeproject.utils.ResponseHTTP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllAccountUseCase @Inject constructor(private val repository: Repository) {

    operator fun invoke(): Flow<ResponseHTTP<AccountResponse>> = flow {
        try {
            emit(ResponseHTTP.Loading())
            val account = repository.getAllAccount()
            //var responseMapper = UserMapper.mapAllUsers(users)

            emit(ResponseHTTP.Success(account))

        }catch (e:Exception){
            emit(ResponseHTTP.Error<AccountResponse>(e.localizedMessage?:"erreur de connexion"))
        }
    }

}



