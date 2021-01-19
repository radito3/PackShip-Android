package com.trifonov.packship.extension

import com.trifonov.packship.MainCoroutineRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineScope
import kotlinx.coroutines.test.runBlockingTest

@ExperimentalCoroutinesApi
fun MainCoroutineRule.runBlockingTest(block: suspend TestCoroutineScope.() -> Unit) {
    testDispatcher.runBlockingTest(block)
}