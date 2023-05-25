package uz.gita.contactappcompose.ui.viewmodel.impl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import uz.gita.contactappcompose.data.common.ContactData
import uz.gita.contactappcompose.domain.repository.AppRepository
import uz.gita.contactappcompose.ui.viewmodel.HomeViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModelImpl @Inject constructor(
    private val repository: AppRepository
) : HomeViewModel, ViewModel() {

    override val contactsLiveData = MutableLiveData<List<ContactData>>()

    init {
        viewModelScope.launch {
            repository.retrieveAllContacts().flowOn(Dispatchers.IO)
                .collect {
                    contactsLiveData.value = it
                }
        }
    }
}