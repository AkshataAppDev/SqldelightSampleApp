package com.example.sqldelightsampleapp.data

import kotlinx.coroutines.flow.Flow
import sampleapp.persondb.PersonEntity

interface PersonDataSource {

    // PersonEntity is generated from the sqldelight file you defined.
    suspend fun getPersonById(id: Long): PersonEntity?

    // not suspend because returning flow
    fun getAllPersons(): Flow<List<PersonEntity>>

    suspend fun insertPerson(firstName: String, lastName: String, id: Long? = null)

    suspend fun deletePersonById(id: Long)

}