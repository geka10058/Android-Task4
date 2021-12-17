package com.example.android_task4.presentation.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task4.R
import com.example.android_task4.data.Book
import com.example.android_task4.databinding.ActivityMainBinding
import com.example.android_task4.presentation.BookViewModel
import com.example.android_task4.presentation.settings.SettingsActivity
import com.example.android_task4.presentation.update.AddUpgradeActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), BooksAdapter.Callbacks {

    lateinit var binding: ActivityMainBinding
    lateinit var booksRecycler: RecyclerView

    private val viewModel: BookViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booksRecycler = binding.recycler
        booksRecycler.layoutManager = LinearLayoutManager(this)

        val bookRVAdapter = BooksAdapter(this)
        booksRecycler.adapter = bookRVAdapter
        viewModel.allBooks.observe(this) { list ->
            list?.let {
                bookRVAdapter.updateList(it)
            }
        }

        binding.addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddUpgradeActivity::class.java)
            startActivity(intent)
        }

        //supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    /*var listener: SharedPreferences.OnSharedPreferenceChangeListener =
        SharedPreferences.OnSharedPreferenceChangeListener { preference, key ->
            val sortingBy = preference.getString("pref_sort", "Sort by Title")
            when (sortingBy) {
                "Sort by Title" -> {viewModel.sortByTitle()
                    Log.d("AppDebug", "SortTitleOK")}
                "Sort by Author" -> {viewModel.sortByAuthor()
                    Log.d("AppDebug", "SortAuthorOK")}
                "Sort by Number of pages" -> {viewModel.sortByNumberPage()
                    Log.d("AppDebug", "SortNUMBErOK")}
                else -> viewModel.allBooks
            }
        }*/

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_settings) {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
        return true
    }

    override fun onBookClick(book: Book) {
        val intent = Intent(this@MainActivity, AddUpgradeActivity::class.java)
        intent.putExtra("bookType", "Edit")
        intent.putExtra("bookTitle", book.bookTitle)
        intent.putExtra("bookAuthor", book.bookAuthor)
        intent.putExtra("bookNumber", book.bookPageNumber)
        intent.putExtra("bookID", book.id)
        startActivity(intent)
    }

    override fun onBookDeleteIconClick(book: Book) {
        viewModel.deleteBook(book)
        Toast.makeText(this, "${book.bookTitle} Deleted", Toast.LENGTH_SHORT).show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("Confirmation")
            setMessage("Are you sure you want to get out")

            setPositiveButton("Yes") { _, _ ->
                super.onBackPressed()
            }

            setNegativeButton("No") { _, _ ->
                /*
                Toast.makeText(this@MainActivity, "Thank you",
                    Toast.LENGTH_LONG).show()*/
            }
            setCancelable(true)
        }.create().show()
    }
}