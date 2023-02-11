package com.orangeproject.orangebank.business.useCases

import android.util.Log
import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.repository.MyRepository
import com.orangeproject.utils.ResponseHTTP
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllAccountUseCase @Inject constructor(private val repository: MyRepository) {

    operator fun invoke(): Flow<ResponseHTTP<List<OrangeAccount>>> = channelFlow {
        try {
            send(ResponseHTTP.Loading())
            val account = repository.getAllAccount()
            send(ResponseHTTP.Success(account))

        }catch (e: HttpException) {
            send(ResponseHTTP.Error(e.localizedMessage?:"une erreur inconnu s'est produit"))
        } catch (e: IOException) {
            send(ResponseHTTP.Error(e.localizedMessage?:"erreur de connexion"))
        }catch (e: Exception) {
            send(ResponseHTTP.Error(e.localizedMessage?:"erreur de connexion"))
        }
    }

}



