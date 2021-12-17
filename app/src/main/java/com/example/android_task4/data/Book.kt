package com.example.android_task4.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "booksTable")
data class Book(
    @ColumnInfo(name = "title") val bookTitle: String,
    @ColumnInfo(name = "author") val bookAuthor: String,
    @ColumnInfo(name = "pageNumber") val bookPageNumber: String,
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
)