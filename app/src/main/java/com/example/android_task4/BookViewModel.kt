package com.example.android_task4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class BookViewModel(application: Application): AndroidViewModel(application) {
    var allBooks: LiveData<List<Book>>
    var repository: BookRepository

    init {
        val dao = BookDatabase.getDatabase(application).getBooksDao()
        repository = BookRepository(dao)
        allBooks = repository.allNotes
    }

}