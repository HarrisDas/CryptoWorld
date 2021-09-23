package com.harris.cryptoworld.viewModel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.harris.cryptoworld.CoroutineTestRule
import com.harris.cryptoworld.data.exception.NetworkErrorException
import com.harris.cryptoworld.domain.interactors.convertcrypto.ConvertCryptoUseCase
import com.harris.cryptoworld.domain.interactors.getallcrypto.GetAllCryptoUseCase
import com.harris.cryptoworld.domain.model.Crypto
import com.harris.cryptoworld.domain.model.CryptoConvert
import com.harris.cryptoworld.presentation.CryptoViewModel
import com.harris.cryptoworld.presentation.ui.UIState
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.verifySequence
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CryptoViewModelTest {

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @InjectMockKs
    lateinit var viewModel: CryptoViewModel

    @MockK
    lateinit var getAllCryptoUseCase: GetAllCryptoUseCase

    @MockK
    lateinit var convertCryptoUseCase: ConvertCryptoUseCase

    @MockK
    lateinit var cryptoListObserver: Observer<UIState<List<Crypto>>>

    @MockK
    lateinit var conversionErrorObserver: Observer<UIState.ErrorState?>

    @MockK
    lateinit var convertedAmountObserver: Observer<Double>

    @MockK
    lateinit var conversionRateObserver: Observer<Double>

    @MockK
    lateinit var enteredAmountObserver: Observer<Double>

    @MockK
    lateinit var currentCryptoObserver: Observer<Crypto>

    @get:Rule
    var rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        viewModel.cryptoList.observeForever(cryptoListObserver)
        viewModel.conversionError.observeForever(conversionErrorObserver)
        viewModel.conversionRate.observeForever(conversionRateObserver)
        viewModel.convertedAmount.observeForever(convertedAmountObserver)
        viewModel.enteredAmount.observeForever(enteredAmountObserver)
        viewModel.currentCrypto.observeForever(currentCryptoObserver)
    }

    @Test
    fun `test get all crypto success`() = coroutineTestRule.runBlockingTest {

        val cryptolist = listOf(Crypto("abc", "symbol", "url"))
        coEvery { getAllCryptoUseCase() } returns UIState.DataState(cryptolist)
        viewModel.getAllCrypto()

        verifySequence {
            cryptoListObserver.onChanged(UIState.LoadingState)
            cryptoListObserver.onChanged(UIState.DataState(cryptolist))
        }

    }

    @Test
    fun `test get all crypto failed`() = coroutineTestRule.runBlockingTest {
        val exception = NetworkErrorException(1, "unable to fetch data")

        coEvery { getAllCryptoUseCase() } returns UIState.ErrorState(exception)

        viewModel.getAllCrypto()

        verifySequence {
            cryptoListObserver.onChanged(UIState.LoadingState)
            cryptoListObserver.onChanged(UIState.ErrorState(exception))
        }
    }

    @Test
    fun `test convert crypto success`() = coroutineTestRule.runBlockingTest {
        val conversion = CryptoConvert(1.0, 1.0)

        coEvery { convertCryptoUseCase(any(), any(), any()) } returns UIState.DataState(conversion)

        viewModel.convertCrypto("A", "B", 1.0)

        verifySequence {
            conversionRateObserver.onChanged(0.0)
            convertedAmountObserver.onChanged(0.0)
            conversionErrorObserver.onChanged(null)
            convertedAmountObserver.onChanged(1.0)
            conversionRateObserver.onChanged(1.0)
        }
    }

    @Test
    fun `test convert crypto failed`() = coroutineTestRule.runBlockingTest {
        val exception = NetworkErrorException(1, "unable to convert")

        coEvery { convertCryptoUseCase(any(), any(), any()) } returns UIState.ErrorState(exception)

        viewModel.convertCrypto("A", "B", 1.0)

        verifySequence {
            conversionErrorObserver.onChanged(null)
            conversionErrorObserver.onChanged(UIState.ErrorState(exception))
        }
    }

    @Test
    fun `test select current crypto `() = coroutineTestRule.runBlockingTest {
        val crypto = Crypto("a", "b", "c")
        viewModel.selectCrypto(crypto)

        verifySequence {
            currentCryptoObserver.onChanged(crypto)
        }
    }

    @Test
    fun `test on Amount change`() = coroutineTestRule.runBlockingTest {

        viewModel.onAmountChange("1.0", 1.0)
        verifySequence {
            convertedAmountObserver.onChanged(0.0)
            enteredAmountObserver.onChanged(0.0)
            enteredAmountObserver.onChanged(1.0)
            convertedAmountObserver.onChanged(1.0)
        }
    }
}