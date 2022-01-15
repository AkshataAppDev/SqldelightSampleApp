package com.example.sqldelightsampleapp.data

import com.example.sqldelightsampleapp.PersonEntity
import kotlinx.coroutines.flow.Flow

interface PersonDataSource {

    // PersonEntity is generated from the sqldelight file you defined.
    suspend fun getPersonById(id: Long): PersonEntity?

    // not suspend because returning flow
    fun getAllPersons(): Flow<List<PersonEntity>>

    suspend fun insertPerson(
        nationality: String,
        firstName: String,
        lastName: String,
        id: Long? = null
    )

    suspend fun deletePersonById(id: Long)

}