package com.vi.lifehackstudioexample.presentation.companies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vi.lifehackstudioexample.base.event.Event
import com.vi.lifehackstudioexample.domain.companies.Company
import com.vi.lifehackstudioexample.domain.companies.GetCompanies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class CompaniesViewModel(
    private val getCompanies: GetCompanies
) : ViewModel() {

    private val _loadingLiveData = MutableLiveData<Boolean>()
    val loadingLiveData: LiveData<Boolean> = _loadingLiveData

    private val _itemsLiveData = MutableLiveData<List<Company>>()
    val itemsLiveData: LiveData<List<Company>> = _itemsLiveData

    private val _errorLiveData = MutableLiveData<Event<String?>>()
    val errorLiveData: LiveData<Event<String?>> = _errorLiveData

    init {
        loadItems()
    }

    private fun loadItems() {
        emitLoading(true)
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val items = getCompanies.execute()
                emitItems(items)
            } catch (t: Throwable) {
                Timber.e(t)
                emitError(t)
            }
            emitLoading(false)
        }
    }

    private fun emitLoading(isLoading: Boolean) {
        _loadingLiveData.postValue(isLoading)
    }

    private fun emitItems(items: List<Company>) {
        _itemsLiveData.postValue(items)
    }

    private fun emitError(t: Throwable) {
        _errorLiveData.postValue(Event(t.message))
    }

}
