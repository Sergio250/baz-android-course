package com.example.cryptochallenge.ui

import android.view.View
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoData
import com.example.cryptochallenge.domain.base.CryptoDetail
import com.example.cryptochallenge.domain.base.CryptoGraph
import com.example.cryptochallenge.usecase.CryptoUseCase
import com.example.cryptochallenge.utils.RequestState
import com.example.cryptochallenge.utils.toArrayList
import com.github.mikephil.charting.data.Entry
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers.io
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CryptoVM @Inject constructor(private val useCase: CryptoUseCase) : ViewModel() {

    private val _cryptoList = MutableLiveData<ArrayList<CryptoCoins>>()
    private val _cryptoDetail = MutableLiveData<CryptoDetail>()
    private val _graphDataObject = MutableLiveData<CryptoGraph>()
    private val _showLoadingLottie = MutableLiveData<Int>()

    val cryptoList: LiveData<ArrayList<CryptoCoins>> get() = _cryptoList
    val cryptoDetail: LiveData<CryptoDetail> get() = _cryptoDetail
    val graphDataObject: LiveData<CryptoGraph> get() = _graphDataObject

    @VisibleForTesting
    fun setList(list: List<CryptoCoins>) {
        _cryptoList.value = list.toArrayList()
    }

    fun getCryptoDetail(book: String) {
        useCase.getCryptoDetail(book).subscribeOn(io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe(object : Observer<CryptoDetail> {
                override fun onSubscribe(d: Disposable) { }
                override fun onNext(detail: CryptoDetail) {
                    _cryptoDetail.postValue(detail)
                }
                override fun onError(e: Throwable) {
                    _cryptoDetail.postValue(CryptoDetail())
                }
                override fun onComplete() {
                }
            })
    }

    fun getAllCryptoCoins() {
        useCase.getAllCryptoCoins().onEach { result ->
            when (result) {
                is RequestState.Success<List<CryptoCoins>> -> {
                    _cryptoList.value = result.data?.filter { it.book?.contains("mxn") == true }?.toArrayList()
                    _showLoadingLottie.value = View.GONE
                }
                is RequestState.Error<List<CryptoCoins>> -> {
                    _cryptoList.value = arrayListOf()
                    _showLoadingLottie.value = View.GONE
                }
                is RequestState.Loading<List<CryptoCoins>> -> { _showLoadingLottie.value = View.VISIBLE }
            }
        }.launchIn(viewModelScope)
    }

    fun getOpenOrders(book: String) {
        useCase.getOpenOrders(book).onEach { result ->
            when (result) {
                is RequestState.Success<CryptoData> -> {
                    val graphYValue = arrayListOf<Entry>()
                    val graphXValues = arrayListOf<String>()
                    result.data?.apply {
                        this.bidsAsks?.forEachIndexed { index, bidsAsk ->
                            graphYValue.add(Entry(bidsAsk.price?.toFloat() ?: 0F, index))
                            graphXValues.add(bidsAsk.amount ?: "")
                        }
                        _graphDataObject.value =
                            CryptoGraph(
                                book = book,
                                yValues = graphYValue,
                                xValues = graphXValues
                            )
                    }
                }
                is RequestState.Error<CryptoData> -> _graphDataObject.value = CryptoGraph()
                is RequestState.Loading<CryptoData> -> { }
            }
        }.launchIn(viewModelScope)
    }
}
