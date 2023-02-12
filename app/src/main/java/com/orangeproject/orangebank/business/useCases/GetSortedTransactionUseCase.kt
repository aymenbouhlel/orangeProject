package com.orangeproject.orangebank.business.useCases

import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.repository.OrangeRepository
import com.orangeproject.utils.Constant
import com.orangeproject.utils.ResponseHTTP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import javax.inject.Inject

class GetSortedTransactionUseCase @Inject constructor(private val repository: OrangeRepository) {

    suspend operator fun invoke(url : String): Flow<ResponseHTTP<List<OrangeTransaction>>> = channelFlow {
        try {
            send(ResponseHTTP.Loading())
            val transaction = repository.getAllTransaction(url)

            val listSortedTransaction = transaction.sortedWith(compareByDescending { it.date }).filter {
                it.status != Constant.statusCanceled
            }

            send(ResponseHTTP.Success(listSortedTransaction))

        }catch (e: Exception) {
            send(ResponseHTTP.Error(e.localizedMessage?:e.message))
        }
    }

}



