package com.example.cryptochallenge.ui

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.*
import com.example.cryptochallenge.usecase.CryptoUseCase
import com.example.cryptochallenge.domain.base.*
import com.example.cryptochallenge.domain.response.TickerResponse
import com.example.cryptochallenge.utils.RequestState
import com.example.cryptochallenge.utils.toArrayList
import com.example.cryptochallenge.utils.toDomain
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CryptoVM @Inject constructor(private val useCase: CryptoUseCase): ViewModel() {

    private val _cryptoList = MutableLiveData<ArrayList<CryptoCoins>>()
    private val _cryptoDetail = MutableLiveData<CryptoDetail>()
    private val _graphDataObject = MutableLiveData<CryptoGraph>()
    private val _isInternetConnectionAvailable = MutableLiveData<Boolean>()
    private val _showLoadingLottie = MutableLiveData<Int>()

    val cryptoList: LiveData<ArrayList<CryptoCoins>> get() = _cryptoList
    val cryptoDetail: LiveData<CryptoDetail> get() = _cryptoDetail
    val graphDataObject: LiveData<CryptoGraph> get() = _graphDataObject
    val isInternetConnectionAvailable: LiveData<Boolean> get() = _isInternetConnectionAvailable

    fun setInternetConnectionVerifier(value: Boolean){
        _isInternetConnectionAvailable.value = value
    }

    @VisibleForTesting
    fun setList(list: List<CryptoCoins>){
        _cryptoList.value = list.toArrayList()
    }

    fun getCryptoDetail(book: String) {
        useCase.getCryptoDetail(book){
            it.subscribeOn(io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object: Observer<TickerResponse>{
                    override fun onSubscribe(d: Disposable) { }
                    override fun onNext(ticker: TickerResponse) {
                        _cryptoDetail.postValue(ticker.payload?.toDomain())
                    }
                    override fun onError(e: Throwable) {
                        _cryptoDetail.postValue(CryptoDetail())
                    }
                    override fun onComplete() { }
                })
        }
    }

    fun getAllCryptoCoins() {
        useCase.getAllCryptoCoins().onEach { result ->
            withContext(IO) {
                when (result) {
                    is RequestState.Success<List<CryptoCoins>> -> {
                        _cryptoList.postValue(result.data?.filter { it.book?.contains("mxn") == true }?.toArrayList())
                        _showLoadingLottie.postValue(View.GONE)
                    }
                    is RequestState.Error<List<CryptoCoins>> ->{
                        _cryptoList.postValue(arrayListOf())
                        _showLoadingLottie.postValue(View.GONE)
                    }
                    is RequestState.Loading<List<CryptoCoins>> -> { _showLoadingLottie.postValue(View.VISIBLE) }
                }
            }
        }.launchIn(viewModelScope)
    }


    fun getOpenOrders(book: String) {
        useCase.getOpenOrders(book).onEach { result ->
            withContext(IO) {
                when (result) {
                    is RequestState.Success<CryptoData> -> {
                        val graphYValue = arrayListOf<Entry>()
                        val graphXValues = arrayListOf<String>()
                        result.data?.apply {
                            this.bidsAsks?.forEachIndexed { index, bidsAsk ->
                                graphYValue.add(Entry(bidsAsk.price?.toFloat() ?: 0F, index))
                                graphXValues.add(bidsAsk.amount ?: "")
                            }
                            _graphDataObject.postValue(CryptoGraph(
                                book = book,
                                yValues = graphYValue,
                                xValues = graphXValues
                            ))
                        }
                    }
                    is RequestState.Error<CryptoData> -> _graphDataObject.value = CryptoGraph()
                    is RequestState.Loading<CryptoData> -> { }
                }
            }
        }.launchIn(viewModelScope)
    }
}


