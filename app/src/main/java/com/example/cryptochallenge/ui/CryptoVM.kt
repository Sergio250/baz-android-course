package com.example.cryptochallenge.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptochallenge.domain.response.AvailableBooksResponse
import com.example.cryptochallenge.domain.response.OrderBookResponse
import com.example.cryptochallenge.usecase.CryptoUseCase
import androidx.lifecycle.viewModelScope
import com.example.cryptochallenge.domain.base.CryptoAsk
import com.example.cryptochallenge.domain.base.CryptoBids
import com.example.cryptochallenge.domain.base.CryptoBook
import com.example.cryptochallenge.domain.base.CryptoDetail
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.utils.RequestState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

class CryptoVM @Inject constructor(private val useCase: CryptoUseCase): ViewModel() {

    private val _cryptoList = MutableLiveData<ArrayList<CryptoCoins>>()
    private val _cryptoDetail = MutableLiveData<CryptoDetail>()
    private val _cryptoBook = MutableLiveData<CryptoBook>()

    val cryptoList: LiveData<ArrayList<CryptoCoins>> get() = _cryptoList
    val cryptoDetail: LiveData<CryptoDetail> get() = _cryptoDetail
    val cryptoBook: LiveData<CryptoBook> get() = _cryptoBook

    private fun setCryptoBook(value: CryptoBook){
        _cryptoBook.value = value
    }

    private fun setCryptoDetail(value: CryptoDetail){
        _cryptoDetail.value = value
    }

    private fun setCryptoList(value: ArrayList<CryptoCoins>){
        _cryptoList.value = value
    }

    fun getDetailCripto() {
        useCase.getDetailCripto().onEach { result ->
            when (result) {
                is RequestState.Success<*> -> {
                    var cryptoDetail: CryptoDetail
                    result.data?.payload.apply {
                        cryptoDetail = CryptoDetail(
                            book = this?.book,
                            volume = this?.volume,
                            high = this?.high,
                            last = this?.last,
                            low = this?.low,
                            vwap = this?.vwap,
                            ask = this?.ask,
                            bid = this?.bid,
                            created_at = this?.createdAt
                        )
                    }
                    setCryptoDetail(cryptoDetail)
                }
                is RequestState.Error<*> -> {
                    _cryptoDetail.value = CryptoDetail()
                }
                is RequestState.Loading<*> -> {
                    //TODO create loading page
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllCriptoCoins() {
        useCase.getAllCriptoCoins().onEach { result ->
            when (result) {
                is RequestState.Success<AvailableBooksResponse> -> {
                    val cryptoList = arrayListOf<CryptoCoins>()
                    result.data?.payload?.forEach { cryptoCoin ->
                        if(cryptoCoin.book?.contains("mxn") == true) {
                            cryptoList += CryptoCoins(
                                book = cryptoCoin.book,
                                minAmount = cryptoCoin.minAmount,
                                maxAmount = cryptoCoin.maxAmount,
                                minPrice = cryptoCoin.minPrice,
                                maxPrice = cryptoCoin.maxPrice,
                                minValue = cryptoCoin.minValue,
                                maxValue = cryptoCoin.maxValue
                            )
                        }
                    }
                    Log.d("prueba", cryptoList.toString())
                    setCryptoList(cryptoList)
                }
                is RequestState.Error<AvailableBooksResponse> -> {
                    _cryptoList.value = arrayListOf()
                }
                is RequestState.Loading<AvailableBooksResponse> -> {
                    //TODO create loading page
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getOpenOrders() {
        useCase.getOpenOrders().onEach { result ->
            when (result) {
                is RequestState.Success<OrderBookResponse> -> {
                    val cryptoAsks = arrayListOf<CryptoAsk>()
                    val cryptoBids = arrayListOf<CryptoBids>()
                    result.data?.payload.apply {
                        this?.asks?.forEach { ask ->
                            cryptoAsks += CryptoAsk(
                                book = ask.book,
                                price = ask.price,
                                amount = ask.amount
                            )
                        }
                        this?.bids?.forEach { bid ->
                            cryptoBids += CryptoBids(
                                book = bid.book,
                                price = bid.price,
                                amount = bid.amount
                            )
                        }
                        setCryptoBook(CryptoBook(
                            cryptoAsks,
                            cryptoBids,
                            this?.updated_at,
                            this?.sequence
                        ))
                    }
                }
                is RequestState.Error<OrderBookResponse> -> {
                    _cryptoBook.value = CryptoBook()
                }
                is RequestState.Loading<OrderBookResponse> -> {
                    //TODO create loading page
                }
            }
        }.launchIn(viewModelScope)
    }

}