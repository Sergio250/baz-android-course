package com.example.cryptochallenge.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.cryptochallenge.domain.base.CryptoBidsAsk
import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.domain.base.CryptoData
import com.example.cryptochallenge.usecase.CryptoUseCase
import com.example.cryptochallenge.utils.RequestState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CryptoVMTest{
    @RelaxedMockK
    private lateinit var cryptoUseCase: CryptoUseCase
    private lateinit var cryptoVM: CryptoVM

    @get:Rule
    var rule: InstantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cryptoVM = CryptoVM(cryptoUseCase)
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    @After
    fun onAfter(){
        Dispatchers.resetMain()
    }

    @Test
    fun `Get all the crypto coins when the viewModel is created`() = runTest {
        val list = listOf(CryptoCoins("btc_mxn", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00"),
        CryptoCoins("dai_mxn", "10.00", "20.00", "30.00", "40.00", "50.00", "60.00"))

        coEvery { cryptoUseCase.getAllCryptoCoins() } returns flow { RequestState.Success<List<CryptoCoins>>(list) }
        cryptoVM.getAllCryptoCoins()
        cryptoVM.cryptoList.value?.equals(list.first())?.let { assert(it) }

    }

    @Test
    fun `Get the bids and asks data when the cardView is clicked`() = runTest {
        val cryptoData = CryptoData(listOf(
            CryptoBidsAsk("btc_mxn","10.00","5.00"),
            CryptoBidsAsk("btc_mxn", "20.00", "4.00")
        ))

        coEvery { cryptoUseCase.getAllCryptoCoins() } returns flow { RequestState.Success<CryptoData>(cryptoData) }
        cryptoVM.getOpenOrders("btc_mxn")
        cryptoVM.cryptoList.value?.equals(cryptoData)?.let { assert(it) }
    }

    @Test
    fun `If the use case returns null in the cryptoList keep the last data`() = runTest {
        val list = listOf(CryptoCoins("btc_mxn", "1.00", "2.00", "3.00", "4.00", "5.00", "6.00"),
            CryptoCoins("dai_mxn", "10.00", "20.00", "30.00", "40.00", "50.00", "60.00"))

        cryptoVM.setList(list)
        coEvery { cryptoUseCase.getAllCryptoCoins() } returns flow { RequestState.Success<List<CryptoCoins>>(
            listOf()
        ) }

        cryptoUseCase.getAllCryptoCoins()

        assert(cryptoVM.cryptoList.value == list)

    }
}

