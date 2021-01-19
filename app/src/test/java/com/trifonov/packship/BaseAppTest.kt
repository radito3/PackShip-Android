package com.trifonov.packship

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.rules.TestRule
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import org.mockito.quality.Strictness

@ExperimentalCoroutinesApi
abstract class BaseAppTest {

    @get:Rule
    val mockitoRule: MockitoRule? = MockitoJUnit.rule().strictness(Strictness.STRICT_STUBS)

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @Before
    abstract fun setUp()

}