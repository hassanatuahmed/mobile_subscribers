package com.example.mobilesub.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.mobilesub.data.models.Subscriber
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface SubscriberDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    suspend fun insertSubscriber(subscriber: Subscriber)

    @Query("SELECT * FROM subscriber_table")
     fun getAllUser(): LiveData<List<Subscriber>>

    @Query("SELECT * FROM subscriber_table")
    fun getAllUserFlow(): Flow<List<Subscriber>>

    @Query("SELECT * FROM subscriber_table WHERE id =:id")
    fun getSubscriber(id : UUID): Flow<Subscriber>

    @Query("DELETE FROM subscriber_table WHERE id = :id")
    fun deleteUser(id: UUID)

//    @Query("SELECT * FROM subscriber_table WHERE subscriberName LIKE : searchQuery OR loc")
//     fun searchDatabase(searchQuery: String):Flow<List<Subscriber>>
}