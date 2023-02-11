package com.orangeproject.orangebank.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.business.useCases.GetAllAccountUseCase
import com.orangeproject.utils.ResponseHTTP
import com.orangeproject.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(val getAllAccountUseCase: GetAllAccountUseCase) : ViewModel() {

    private val _accountLiveData: MutableLiveData<List<OrangeAccount>> = MutableLiveData()
    val listAccount: LiveData<List<OrangeAccount>>
        get() = _accountLiveData

    private val _updateUiState: MutableLiveData<UiState> = MutableLiveData()
    val updateUiState: LiveData<UiState>
        get() = _updateUiState

    init {

        viewModelScope.launch {
            getAllAccount()
        }
    }

    suspend fun getAllAccount(){
        getAllAccountUseCase().onEach { result ->
            when(result){

                is ResponseHTTP.Loading -> {

                    sendUiState(UiState.SHOW_LOADING)
                }
                is ResponseHTTP.Error -> {
                    sendUiState(UiState.ERROR)
                }
                is ResponseHTTP.Success -> {

                    sendUiState(UiState.HIDE_LOADING)
                  //  _accountLiveData.postValue(result.data)
                    result.data.let { _accountLiveData.postValue(it) }
                }
            }
        }.collect()
    }

    private fun sendUiState(stateUi: UiState) {
        _updateUiState.postValue(stateUi)
    }

}