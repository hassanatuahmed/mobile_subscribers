package com.example.mobilesub.data.models

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

@Entity(tableName = "subscriber_table")
 data class Subscriber (

    @PrimaryKey(autoGenerate = true)
    @NonNull
    var id: Int=0,
    var subscriberName: String ,
    var email: String,
    var phoneNumber: String ,
    var dateOfBirth: String ,
    var location: String ,
    var status: String

//    constructor() {}
//
//    constructor(
//        id: Int,
//        subscriberName: String,
//        email: String,
//        phoneNumber: String,
//        dateOfBirth: String,
//        location: String,
//        status: String
//    ) {
//        this.email = email
//        this.subscriberName = subscriberName
//        this.phoneNumber = phoneNumber
//        this.dateOfBirth = dateOfBirth
//        this.location = location
//        this.status = status
//    }
//
//    constructor(
//        subscriberName: String,
//        email: String,
//        phoneNumber: String,
//        dateOfBirth: String,
//        location: String,
//        status: String
//    ) {
//        this.email = email
//        this.subscriberName = subscriberName
//        this.phoneNumber = phoneNumber
//        this.dateOfBirth = dateOfBirth
//        this.location = location
//        this.status = status
//    }


)