package com.orangeproject.orangebank.business.useCases

import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.repository.MyRepository
import com.orangeproject.utils.ResponseHTTP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetTransactionUseCase @Inject constructor(private val repository: MyRepository) {

    operator fun invoke(url : String): Flow<ResponseHTTP<List<OrangeTransaction>>> = channelFlow {
        try {
            send(ResponseHTTP.Loading())
            val transaction = repository.getAllTransaction(url)
            send(ResponseHTTP.Success(transaction))

        }catch (e: HttpException) {
            send(ResponseHTTP.Error(""))
        } catch (e: IOException) {
            send(ResponseHTTP.Error(""))
        }catch (e: Exception) {
            send(ResponseHTTP.Error(""))
        }
    }

}



