@file:Suppress("SpellCheckingInspection")

package com.example.cryptochallenge.usecase

import com.example.cryptochallenge.domain.base.CryptoCoins
import com.example.cryptochallenge.repository.CryptoRepositoryImp
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class CryptoUseCaseTest {
    @RelaxedMockK
    private lateinit var repository: CryptoRepositoryImp
    private lateinit var cryptoUseCase: CryptoUseCase

    @Before
    fun onBefore() {
        MockKAnnotations.init(this)
        cryptoUseCase = CryptoUseCase(repository)
    }

    @Test
    fun getDataFromDatabase() = runBlocking {
        // Given
        coEvery { repository.getAllCryptoCoinsFromApi() } returns emptyList()
        // When
        cryptoUseCase.getAllCryptoCoins()
        // Then
        coVerify(exactly = 1) { repository.getAllCryptoCoinsFromDatabase() }
    }

    @Test
    fun dontGetDataFromDatabase() = runBlocking {
        // Given
        val list =
            listOf(CryptoCoins("btc_mxn", "10.00", "1.00", "0.5", "15.00", "5.00", "4.00"))
        coEvery { repository.getAllCryptoCoinsFromApi() } returns list
        // When
        cryptoUseCase.getAllCryptoCoins()
        // Then
        coVerify(exactly = 0) { repository.getAllCryptoCoinsFromDatabase() }
    }
}
