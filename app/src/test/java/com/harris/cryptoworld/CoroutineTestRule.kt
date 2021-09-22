package com.harris.cryptoworld

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.rules.TestWatcher
import org.junit.runner.Description

@ExperimentalCoroutinesApi
class CoroutineTestRule : TestWatcher() {

    val dispatcher = TestCoroutineDispatcher()

    override fun starting(description: Description?) {
        super.starting(description)
        kotlinx.coroutines.Dispatchers.setMain(dispatcher)
    }

    override fun finished(description: Description?) {
        super.finished(description)
        kotlinx.coroutines.Dispatchers.resetMain()
        dispatcher.cleanupTestCoroutines()
    }

    fun runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
        dispatcher.runBlockingTest(block)
    }
}