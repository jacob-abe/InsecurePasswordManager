package com.example.passwordmanager.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Password(
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name = "website_name")
    var websiteName: String?,
    @ColumnInfo(name = "domain_name")
    val domainName: String,
    @ColumnInfo(name = "password")
    var password: String
){
    constructor():this(0,null,"","")
}
