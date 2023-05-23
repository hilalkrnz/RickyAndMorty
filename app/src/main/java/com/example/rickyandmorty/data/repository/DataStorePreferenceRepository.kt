package com.example.rickyandmorty.data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStorePreferenceRepository @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    companion object {
        val CURRENT_USER = booleanPreferencesKey("current_user")
    }

    suspend fun setCurrentUser(currentUser: Boolean) {
        dataStore.edit { preferences ->
            preferences[CURRENT_USER] = currentUser
        }
    }

    val getCurrentUser = dataStore.data.map {
        it[CURRENT_USER] ?: false
    }
}