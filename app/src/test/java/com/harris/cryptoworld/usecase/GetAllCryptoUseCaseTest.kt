package com.harris.cryptoworld.usecase

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harris.cryptoworld.CoroutineTestRule
import com.harris.cryptoworld.data.exception.NetworkErrorException
import com.harris.cryptoworld.data.remote.model.ApiErrorResponse
import com.harris.cryptoworld.data.remote.model.CryptoListResponse
import com.harris.cryptoworld.data.remote.model.RemoteCrypto
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCaseImpl
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
class GetAllCryptoUseCaseTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var useCase: GetAllCryptoUseCase

    @MockK
    lateinit var repository: ICryptoRepository

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        useCase = GetAllCryptoUseCaseImpl(repository, NetworkUtils(context))
    }

    @Test
    fun `test use case success`() = coroutineTestRule.runBlockingTest {
        val response = CryptoListResponse().apply {
            success = true
            crypto = mapOf(Pair("A", RemoteCrypto().apply { name = "A" }))
        }
        coEvery { repository.getAllCrypto() } returns response

        val useCase = useCase()
        assert(useCase is UIState.DataState)
        assertEquals(
            (useCase as? UIState.DataState)?.data,
            response.crypto?.map { it.value.toBean() })

    }


    @Test
    fun `test use case failed`() = coroutineTestRule.runBlockingTest {
        val response = CryptoListResponse().apply {
            success = false
            error = ApiErrorResponse().apply { info = "unable to etch data" }
        }
        coEvery { repository.getAllCrypto() } returns response

        val useCase = useCase()
        assert(useCase is UIState.ErrorState)
        assertEquals(
            ((useCase as? UIState.ErrorState)?.exception as? NetworkErrorException)?.errorMessage,
            response.error?.info
        )

    }
}