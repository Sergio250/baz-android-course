package com.example.cryptochallenge.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptochallenge.usecase.CryptoUseCase
import androidx.lifecycle.viewModelScope
import com.example.cryptochallenge.R
import com.example.cryptochallenge.domain.base.*
import com.example.cryptochallenge.utils.RequestState
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoVM @Inject constructor(private val useCase: CryptoUseCase): ViewModel() {

    private val _cryptoList = MutableLiveData<ArrayList<CryptoCoins>>()
    private val _cryptoDetail = MutableLiveData<CryptoDetail>()
    private val _graphDataObject = MutableLiveData<CryptoGraph>()

    val cryptoList: LiveData<ArrayList<CryptoCoins>> get() = _cryptoList
    val cryptoDetail: LiveData<CryptoDetail> get() = _cryptoDetail
    val graphDataObject: LiveData<CryptoGraph> get() = _graphDataObject


    private fun setCryptoDetail(value: CryptoDetail){
        _cryptoDetail.value = value
    }

    private fun setCryptoList(value: ArrayList<CryptoCoins>){
        _cryptoList.value = value
    }

    private fun setCryptoDataObject(book: String,
            bidsValues: ArrayList<Entry>,
            asksValues: ArrayList<Entry>,
            xValues: ArrayList<String>,
            labels: ArrayList<String>,
            colors: ArrayList<Int>){
        _graphDataObject.value = CryptoGraph(book, bidsValues, asksValues, xValues, labels, colors)
    }

    fun getDetailCrypto(book: String) {
        useCase.getDetailCrypto(book).onEach { result ->
            when (result) {
                is RequestState.Success<CryptoDetail> -> {
                    var cryptoDetail = CryptoDetail()
                    result.data?.apply {
                        cryptoDetail = CryptoDetail(
                            book = this.book,
                            volume = this.volume,
                            high = this.high,
                            last = this.last,
                            low = this.low,
                            vwap = this.vwap,
                            ask = this.ask,
                            bid = this.bid,
                            created_at = this.created_at
                        )
                    }
                    setCryptoDetail(cryptoDetail)
                }
                is RequestState.Error<CryptoDetail> -> {
                    _cryptoDetail.value = CryptoDetail()
                }
                is RequestState.Loading<CryptoDetail> -> {
                    //TODO create loading page
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getAllCryptoCoins() {
        useCase.getAllCryptoCoins().onEach { result ->
            when (result) {
                is RequestState.Success<List<CryptoCoins>> -> {
                    val cryptoList = arrayListOf<CryptoCoins>()
                    result.data?.forEach { cryptoCoin ->
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
                    setCryptoList(cryptoList)
                }
                is RequestState.Error<List<CryptoCoins>> -> {
                    _cryptoList.value = arrayListOf()
                }
                is RequestState.Loading<List<CryptoCoins>> -> {
                    //TODO create loading page
                }
            }
        }.launchIn(viewModelScope)
    }

    fun getOpenOrders(book: String) {
        useCase.getOpenOrders(book).onEach { result ->
            when (result) {
                is RequestState.Success<CryptoBook> -> {
                    val graphAsksYValue = arrayListOf<Entry>()
                    val graphBidsYValue = arrayListOf<Entry>()
                    val graphXValues = arrayListOf<String>()
                    result.data?.apply {
                        this.asks?.forEachIndexed { index, ask ->
                            graphAsksYValue.add(Entry(ask.price?.toFloat() ?: 0F, index))
                            graphXValues.add(ask.amount ?: "")
                        }
                        this.bids?.forEachIndexed { index, bid ->
                            graphBidsYValue.add(Entry(bid.price?.toFloat() ?: 0F, index))
                            graphXValues.add(bid.amount ?: "")
                        }
                        setCryptoDataObject(
                            book = book,
                            bidsValues = graphBidsYValue,
                            asksValues = graphAsksYValue,
                            xValues = graphXValues,
                            labels = arrayListOf("Bids", "Asks"),
                            colors = arrayListOf(R.color.black, R.color.orange)
                        )
                    }
                }
                is RequestState.Error<CryptoBook> -> {
                    _graphDataObject.value = CryptoGraph()
                }
                is RequestState.Loading<CryptoBook> -> {
                    //TODO create loading page
                }
            }
        }.launchIn(viewModelScope)
    }

}