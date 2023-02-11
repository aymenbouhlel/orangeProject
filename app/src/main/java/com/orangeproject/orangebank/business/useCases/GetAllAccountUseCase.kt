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
            //var responseMapper = mapper.mapAllAccount(account)
            send(ResponseHTTP.Success(account))

        }catch (e: HttpException) {
            Log.i("aymsoft",  " error 77")
            send(ResponseHTTP.Error(e.localizedMessage?:"une erreur inconnu s'est produit"))
        } catch (e: IOException) {
            Log.i("aymsoft", " error 66")
            send(ResponseHTTP.Error(e.localizedMessage?:"erreur de connexion"))
        }catch (e: Exception) {
            Log.i("aymsoft", " error 55")
            send(ResponseHTTP.Error(e.localizedMessage?:"erreur de connexion"))
        }
    }

}



