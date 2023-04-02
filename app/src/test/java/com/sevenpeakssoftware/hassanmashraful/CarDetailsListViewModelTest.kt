package com.sevenpeakssoftware.hassanmashraful

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sevenpeakssoftware.hassanmashraful.di.StringResourceProvider
import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDetails
import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDescription
import com.sevenpeakssoftware.hassanmashraful.repository.CarListRepository
import com.sevenpeakssoftware.hassanmashraful.util.Status
import com.sevenpeakssoftware.hassanmashraful.viewmodel.CarListViewModel
import io.mockk.coEvery
import io.mockk.mockk
import io.realm.RealmList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(manifest = Config.NONE)
class CarDetailsListViewModelTest {
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var carListViewModel: CarListViewModel
    private lateinit var repository: CarListRepository
    private lateinit var stringProvider: StringResourceProvider

    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        repository = mockk()
        stringProvider = mockk()

        carListViewModel = CarListViewModel(repository, stringProvider)
    }

    @Test
    fun `shows error while loading data`() = testDispatcher.runBlockingTest {
        coEvery { repository.fetchCarList() } returns Status.Error("Something went wrong")
        carListViewModel.loadData()
        assertThat(carListViewModel.errorMsg.value).isNotEmpty()
    }

    @Test
    fun `fetch car list successfully`() = testDispatcher.runBlockingTest {
        val carDetailsList = mutableListOf<CarDetails>()
        carDetailsList.add(
            CarDetails(
                1,
                "Q7",
                "25.05.2018 14:13",
                RealmList(
                    CarDescription(
                        "Q7",
                        "The Audi Q7 is masculine, yet exudes lightness. Inside, it offers comfort at the highest level."
                    )
                ),
                "The Audi Q7 is the result of an ambitious idea: never cease to improve.",
                "audi_q7.jpg"
            )
        )
        coEvery { repository.fetchCarList() } returns Status.Success(carDetailsList)
        carListViewModel.loadData()
        assertThat(carListViewModel.carList.value.size).isEqualTo(1)
    }

    @Test
    fun `shows error while get car list from local db`() = testDispatcher.runBlockingTest {
        coEvery { repository.getCarList() } returns Status.Error("Something went wrong")
        carListViewModel.loadLocalData()
        assertThat(carListViewModel.errorMsg.value).isNotEmpty()
    }

    @Test
    fun `get car list successfully from local db`() = testDispatcher.runBlockingTest {
        val carDetailsList = mutableListOf<CarDetails>()
        carDetailsList.add(
            CarDetails(
                1,
                "Q7",
                "25.05.2018 14:13",
                RealmList(
                    CarDescription(
                        "Q7",
                        "The Audi Q7 is masculine, yet exudes lightness. Inside, it offers comfort at the highest level."
                    )
                ),
                "The Audi Q7 is the result of an ambitious idea: never cease to improve.",
                "audi_q7.jpg"
            )
        )
        coEvery { repository.getCarList() } returns Status.Success(carDetailsList)
        carListViewModel.loadLocalData()
        assertThat(carListViewModel.carList.value.size).isEqualTo(1)
    }
}