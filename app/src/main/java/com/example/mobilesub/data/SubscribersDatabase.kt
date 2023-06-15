package com.example.mobilesub.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobilesub.data.models.Subscriber

@Database(entities = [(Subscriber::class)], version = 1)

abstract class SubscribersDatabase : RoomDatabase() {

    abstract fun subscriberDao(): SubscriberDao

    companion object {
        private var INSTANCE: SubscribersDatabase? = null
        fun getInstance(context: Context): SubscribersDatabase {

            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        SubscribersDatabase::class.java,
                        "subscriber_database"
                    ).fallbackToDestructiveMigration().build()
                    INSTANCE = instance
                }
                return instance
            }

        }

    }
}