package com.example.passwordmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SecureNote(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "data")
    var data: String
){
    constructor():this(0,"","")
}