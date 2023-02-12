package com.orangeproject.orangebank.ui.account

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.orangeproject.orangebank.business.models.OrangeAccount
import com.orangeproject.orangebank.business.models.OrangeTransaction
import com.orangeproject.orangebank.business.useCases.GetAllAccountUseCase
import com.orangeproject.orangebank.business.useCases.GetSortedTransactionUseCase
import com.orangeproject.orangebank.ui.transaction.mapper.TransactionUiMapper
import com.orangeproject.utils.ResponseHTTP
import com.orangeproject.utils.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountViewModel @Inject constructor(
    val getAllAccountUseCase: GetAllAccountUseCase,
    val getTransactionUseCase: GetSortedTransactionUseCase
) : ViewModel() {

    private val _accountLiveData: MutableLiveData<List<OrangeAccount>> = MutableLiveData()
    val listAccount: LiveData<List<OrangeAccount>>
        get() = _accountLiveData

    private val _updateUiState: MutableLiveData<UiState> = MutableLiveData()
    val updateUiState: LiveData<UiState>
        get() = _updateUiState

    private val _transactionLiveData: MutableLiveData<Pair<List<OrangeTransaction>, List<OrangeTransaction>>> =
        MutableLiveData()
    val listTransaction: LiveData<Pair<List<OrangeTransaction>, List<OrangeTransaction>>>
        get() = _transactionLiveData

    init {
        getAllAccount()
    }

    fun getAllAccount() {
        viewModelScope.launch {
            getAllAccountUseCase().onEach { result ->
                when (result) {

                    is ResponseHTTP.Loading -> {
                        sendUiState(UiState.SHOW_LOADING)
                    }
                    is ResponseHTTP.Error -> {
                        sendUiState(UiState.ERROR)
                    }
                    is ResponseHTTP.Success -> {

                        sendUiState(UiState.HIDE_LOADING)

                        _accountLiveData.value = result.data?.let {
                            it
                        } ?: emptyList()
                    }
                }
            }.collect()
        }
    }

    fun getTransaction(url: String) {

        viewModelScope.launch {
            getTransactionUseCase(url).onEach { result ->
                when (result) {
                    is ResponseHTTP.Loading -> {
                        sendUiState(UiState.SHOW_LOADING)
                    }
                    is ResponseHTTP.Error -> {
                        sendUiState(UiState.ERROR)
                    }
                    is ResponseHTTP.Success -> {
                        _transactionLiveData.value = result.data?.let {
                            TransactionUiMapper.mapTransaction(it)
                        }
                    }
                }
            }.collect()
        }
    }

    private fun sendUiState(stateUi: UiState) {
        _updateUiState.value = stateUi
    }

}