package com.example.handlerasynckrxkotlin.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id:Int = 0,
    val title:String,
    val description:String
)
