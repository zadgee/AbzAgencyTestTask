package manager

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

private val Context.dataStore by preferencesDataStore("datastore_preferences")

class DataStoreManager(private val context:Context) {

    suspend fun saveToken(token:String){
        withContext(Dispatchers.IO){
            context.dataStore.edit { preferences->
                preferences[stringPreferencesKey("token")] = token
            }
        }
    }

     suspend fun getToken():String?{
        return context.dataStore.data.map { preferences->
            preferences[stringPreferencesKey("token")]
        }.flowOn(Dispatchers.IO).first()
    }

}