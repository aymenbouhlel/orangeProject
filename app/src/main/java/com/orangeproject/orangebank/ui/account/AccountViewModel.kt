package com.orangeproject.orangebank.ui.account

import android.util.Log
import androidx.lifecycle.ViewModel
import com.orangeproject.orangebank.business.useCases.GetAllAccountUseCase
import com.orangeproject.utils.ResponseHTTP
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import javax.inject.Inject



@HiltViewModel
class AccountViewModel @Inject constructor(val getAllAccountUseCase: GetAllAccountUseCase) : ViewModel() {

    init {
        runBlocking {
            getAllAccount()
        }
    }

    private suspend fun getAllAccount(){
        getAllAccountUseCase().onEach { result ->
            when(result){

                is ResponseHTTP.Loading -> { Log.i("aymsoft", "is Loading")}
                is ResponseHTTP.Error -> { Log.i("aymsoft", "is Error")}
                is ResponseHTTP.Success -> { Log.i("aymsoft", "is Success :")
                    Log.i("aymsoft", "is result  :"+result.data?.get(0).toString())
                }
            }
        }.collect()
    }

}