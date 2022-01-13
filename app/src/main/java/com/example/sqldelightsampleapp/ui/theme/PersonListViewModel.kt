package com.example.sqldelightsampleapp.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sqldelightsampleapp.data.PersonDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import sampleapp.persondb.PersonEntity
import javax.inject.Inject

@HiltViewModel
class PersonListViewModel @Inject constructor(private val personDataSource: PersonDataSource) :
    ViewModel() {

    val persons = personDataSource.getAllPersons()

    // we by using private set only this viewmodel can change its value
    var personDetails by mutableStateOf<PersonEntity?>(null)
        private set

    var firstNameText by mutableStateOf("")
        private set

    var lastNameText by mutableStateOf("")
        private set

    fun onInsertPersonClick() {
        if (firstNameText.isBlank() || lastNameText.isBlank()) {
            return
        }

        viewModelScope.launch {
            personDataSource.insertPerson(firstName = firstNameText, lastName = lastNameText)
            firstNameText = ""
            lastNameText = ""
        }
    }

    fun onDeleteClick(id: Long) {
        viewModelScope.launch {
            personDataSource.deletePersonById(id)
        }
    }

    fun getPersonById(id: Long) {
        viewModelScope.launch {
            personDetails = personDataSource.getPersonById(id)
        }
    }

    fun onFirstNameChange(value: String) {
        firstNameText = value
    }

    fun onLastNameChange(value: String) {
        lastNameText = value
    }

    fun onPersonDetailsDialogDismiss() {
        personDetails = null
    }
}