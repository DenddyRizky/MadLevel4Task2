package com.example.madlevel4task2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "gameTable")
data class Game(

    @ColumnInfo(name = "computerMove")
    var computerMove: Int,

    @ColumnInfo(name = "playerMove")
    var playerMove: Int,

    @ColumnInfo(name = "gameResult")
    var gameResult: Int,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Long? = null
)
