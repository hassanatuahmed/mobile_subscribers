package com.example.mobilesub.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "subscriber_table")
data class Subscriber(
    @PrimaryKey
    @NonNull
    val id: UUID = UUID.randomUUID(),

    var subscriberName: String,

    var email: String,
    var phoneNumber: String,
    var dateOfBirth: String,
    var location: String,
    var status: String


)