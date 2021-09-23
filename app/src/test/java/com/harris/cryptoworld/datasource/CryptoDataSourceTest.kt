package com.harris.cryptoworld.datasource

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.harris.cryptoworld.CoroutineTestRule
import com.harris.cryptoworld.data.datasource.CryptoDataSource
import com.harris.cryptoworld.data.service.CryptoApiService
import io.mockk.MockKAnnotations
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class CryptoDataSourceTest {
    private var mockWebServer = MockWebServer()

    @get:Rule
    val coroutineTestRule = CoroutineTestRule()

    @get:Rule
    var rule = InstantTaskExecutorRule()

    lateinit var dataSource: CryptoDataSource


    lateinit var service: CryptoApiService

    @Before
    fun setUp() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CryptoApiService::class.java)

        dataSource = CryptoDataSource(service)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun `get all crypto success`() = runBlocking {
        enqueueResponse("get-all-crypto-success.json")
        val allCrypto = dataSource.getAllCrypto()

        assertEquals(allCrypto.success, true)
        assertEquals(allCrypto.error, null)
        assert(allCrypto.crypto!!.isNotEmpty())
    }

    @Test
    fun `get all crypto failed`() = runBlocking {
        enqueueResponse("error-response.json")
        val allCrypto = dataSource.getAllCrypto()

        assertEquals(allCrypto.success, false)
        assertEquals(allCrypto.crypto, null)
        assertEquals(allCrypto.error?.code, 400)
        assertEquals(allCrypto.error?.info, "api error")
    }


    @Test
    fun `get convert crypto success`() = runBlocking {
        enqueueResponse("convert-crypto-success.json")
        val allCrypto = dataSource.convertCrypto("A", "B", 1.0)

        assertEquals(allCrypto.success, true)
        assertEquals(allCrypto.error, null)
        assertEquals(allCrypto.result, 1.0)
        assertEquals(allCrypto.info?.rate, 1.0)
    }

    @Test
    fun `get convert crypto failed`() = runBlocking {
        enqueueResponse("error-response.json")
        val allCrypto = dataSource.convertCrypto("A", "B", 1.0)

        assertEquals(allCrypto.success, false)
        assertEquals(allCrypto.result, 0.0)
        assertEquals(allCrypto.error?.code, 400)
        assertEquals(allCrypto.error?.info, "api error")
    }

    private fun enqueueResponse(fileName: String, httpStatusCode: Int = 200) {
        val inputStream = javaClass.classLoader
            ?.getResourceAsStream(fileName)
        inputStream?.source()?.let {
            val source = it.buffer()
            val mockResponse = MockResponse()
            mockResponse.setResponseCode(httpStatusCode)
            mockWebServer.enqueue(
                mockResponse.setBody(
                    source.readString(Charsets.UTF_8)
                )
            )
        }

    }
}