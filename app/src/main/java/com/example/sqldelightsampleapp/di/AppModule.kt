package com.example.sqldelightsampleapp.di

import android.app.Application
import com.example.sqldelightsampleapp.PersonDatabase
import com.example.sqldelightsampleapp.data.PersonDataSource
import com.example.sqldelightsampleapp.data.PersonDataSourceImpl
import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import javax.inject.Singleton

@Module
@InstallIn()
object AppModule {

    @Provides
    @Singleton
    fun providesSqlDriver(app: Application): SqlDriver {
        return AndroidSqliteDriver(
            schema = PersonDatabase.Schema,
            context = app,
            name = "person.db"
        )
    }

    @Provides
    @Singleton
    fun providesPersonDataSource(driver: SqlDriver): PersonDataSource {
        return PersonDataSourceImpl(PersonDatabase(driver))
    }
}