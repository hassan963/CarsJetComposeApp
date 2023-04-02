package com.sevenpeakssoftware.hassanmashraful.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sevenpeakssoftware.hassanmashraful.R
import com.sevenpeakssoftware.hassanmashraful.di.StringResourceProvider
import com.sevenpeakssoftware.hassanmashraful.domain.model.CarDetails
import com.sevenpeakssoftware.hassanmashraful.repository.CarListRepository
import com.sevenpeakssoftware.hassanmashraful.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val repository: CarListRepository,
    private val stringResourceProvider: StringResourceProvider
) : ViewModel() {
    private val _carDetailsList = MutableStateFlow<List<CarDetails>>(listOf())
    val carList get() = _carDetailsList

    private val _isLoading = MutableStateFlow(false)
    val isLoading get() = _isLoading

    private val _errorMsg = MutableStateFlow("")
    val errorMsg get() = _errorMsg

    init {
        loadData()
        _errorMsg.value = ""
    }

    fun loadData() {
        _isLoading.value = true

        viewModelScope.launch {
            when (val status = repository.fetchCarList()) {
                is Status.Success -> {
                    status.data?.let {
                        _carDetailsList.value = it

                        repository.insertCarList(it)
                    }

                    _isLoading.value = false
                }
                is Status.Error -> {
                    loadLocalData()

                    if (_carDetailsList.value.isEmpty()) {
                        _errorMsg.value = status.message
                            ?: stringResourceProvider.getString(R.string.something_went_wrong)
                    }

                    _isLoading.value = false
                }
                else -> {
                    _carDetailsList.value = emptyList()
                }
            }
        }
    }

    suspend fun loadLocalData() {
        when (val status = repository.getCarList()) {
            is Status.Success -> {
                if (!status.data.isNullOrEmpty()) {
                    _carDetailsList.value = status.data
                }
            }
            is Status.Error -> {
                _errorMsg.value = status.message
                    ?: stringResourceProvider.getString(R.string.something_went_wrong)
            }
            else -> {
                _carDetailsList.value = emptyList()
            }
        }
    }
}