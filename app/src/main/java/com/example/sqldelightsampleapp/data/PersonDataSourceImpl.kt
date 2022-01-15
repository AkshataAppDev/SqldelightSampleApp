package com.example.sqldelightsampleapp.data

import com.example.sqldelightsampleapp.PersonDatabase
import com.example.sqldelightsampleapp.PersonEntity
import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

//PersonDatabase is generated from sqldelight
// TODO : Inject Dispatchers
class PersonDataSourceImpl(db: PersonDatabase) : PersonDataSource {

    private val queries = db.personEntityQueries

    override suspend fun getPersonById(id: Long): PersonEntity? {
        return withContext(Dispatchers.IO)
        {
            queries.getPersonById(id).executeAsOneOrNull()
        }
    }

    override fun getAllPersons(): Flow<List<PersonEntity>> {
        return queries.getAllPersons().asFlow().mapToList()
    }

    override suspend fun insertPerson(
        nationality: String,
        firstName: String,
        lastName: String,
        id: Long?
    ) {
        withContext(Dispatchers.IO)
        {
            queries.insertPerson(id, firstName, lastName, nationality)
        }
    }

    override suspend fun deletePersonById(id: Long) {
        withContext(Dispatchers.IO)
        {
            queries.deletePersonById(id)
        }
    }
}