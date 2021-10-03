package com.example.android_task4

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booksTable")
class Book (
    @ColumnInfo(name = "title") val bookTitle:String,
    @ColumnInfo(name = "author") val bookAuthor:String,
    @ColumnInfo(name = "pageNumber") val bookPageNumber:String
    ) {
    @PrimaryKey(autoGenerate = true)
    var id = 0
}