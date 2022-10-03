@file:Suppress("SpellCheckingInspection")

package com.example.cryptochallenge.usecase

import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.repository.CryptoRepositoryImp
import com.example.cryptochallenge.utils.toDatabase
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import java.io.IOException

class CryptoUseCaseTest{
    @RelaxedMockK
    private lateinit var repository: CryptoRepositoryImp
    private lateinit var cryptoUseCase: CryptoUseCase

    @Before
    fun onBefore(){
        MockKAnnotations.init(this)
        cryptoUseCase = CryptoUseCase(repository)
    }

    @Test
    fun `When the api doesnt return a list of crypto coins then get values from database`() = runBlocking {
        coEvery { repository.getAllCryptoCoinsFromApi() } returns emptyList()

        cryptoUseCase.getAllCryptoCoins()

        coVerify(exactly = 1) { repository.getAllCryptoCoinsFromDatabase() }
    }

    @Test
    fun `When the api returns a list of crypto coins then get the values from api`() = runBlocking {
        val list =
            listOf(CryptoCoins("btc_mxn", "10.00", "1.00", "0.5", "15.00", "5.00", "4.00"))

        coEvery { repository.getAllCryptoCoinsFromApi() } returns list

        cryptoUseCase.getAllCryptoCoins()

        coVerify(exactly = 1) { repository.getAllCryptoCoinsFromApi() }
    }

}