package com.harris.cryptoworld.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harris.cryptoworld.CoroutineTestRule
import com.harris.cryptoworld.data.exception.NetworkErrorException
import com.harris.cryptoworld.data.remote.model.ApiErrorResponse
import com.harris.cryptoworld.data.remote.model.CryptoConvertInfo
import com.harris.cryptoworld.data.remote.model.CryptoConvertResponse
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCase
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCaseImpl
import com.harris.cryptoworld.domain.repository.ICryptoRepository
import com.harris.cryptoworld.presentation.ui.UIState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class ConvertCryptoUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var useCase: ConvertCryptoUseCase

    @MockK
    lateinit var repository: ICryptoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = ConvertCryptoUseCaseImpl(repository)
    }

    @Test
    fun `test use case success`() = coroutineTestRule.runBlockingTest {
        val response = CryptoConvertResponse().apply {
            success = true
            result = 1.0
            info = CryptoConvertInfo().apply {
                rate = 5.0
            }

        }
        coEvery { repository.convertCrypto("A", "B", 1.0) } returns response

        val useCase = useCase("A", "B", 1.0)
        assert(useCase is UIState.DataState)
        assertEquals(
            (useCase as? UIState.DataState)?.data,
            response.toBean()
        )

    }


    @Test
    fun `test use case failed`() = coroutineTestRule.runBlockingTest {

        val response = CryptoConvertResponse().apply {
            success = false
            error = ApiErrorResponse().apply { }
        }
        coEvery { repository.convertCrypto("A", "B", 1.0) } returns response

        val useCase = useCase("A", "B", 1.0)
        assert(useCase is UIState.ErrorState)
        assertEquals(
            ((useCase as? UIState.ErrorState)?.exception as? NetworkErrorException)?.errorMessage,
            "Unable to convert! "
        )

    }
}