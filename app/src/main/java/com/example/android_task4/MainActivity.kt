package com.example.android_task4


import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(),BookClickInterface,BookClickDeleteInterface {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: BookViewModel
    lateinit var booksRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booksRecycler = binding.recycler
        booksRecycler.layoutManager = LinearLayoutManager(this)

        val bookRVAdapter = BookRVAdapter(this, this, this)
        booksRecycler.adapter = bookRVAdapter
        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(BookViewModel::class.java)
        viewModel.allBooks.observe(this, { list ->
            list?.let {
                bookRVAdapter.updateList(it)
            }
        })

        binding.addButton.setOnClickListener {
            val intent = Intent(this@MainActivity, AddUpgradeActivity::class.java)
            startActivity(intent)
            this.finish()
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

    override fun onResume() {
        super.onResume()
        val preference = PreferenceManager.getDefaultSharedPreferences(this)
        val sortingBy = preference.getString("pref_sort", "Sort by Title")

        when (sortingBy) {
            "Sort by Title" -> {
                val data = viewModel.sortByTitle()
                Log.d("AppDebug", "SortTitleOK")
            }
            "Sort by Author" -> {
                viewModel.sortByAuthor()
                Log.d("AppDebug", "SortAuthorOK")
            }
            "Sort by Number of pages" -> {
                viewModel.sortByNumberPage()
                Log.d("AppDebug", "SortNUMBErOK")
            }
            else -> viewModel.allBooks
        }
       // preference.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onBookClick(book: Book) {
        val intent = Intent(this@MainActivity, AddUpgradeActivity::class.java)
        intent.putExtra("bookType", "Edit")
        intent.putExtra("bookTitle", book.bookTitle)
        intent.putExtra("bookAuthor", book.bookAuthor)
        intent.putExtra("bookNumber", book.bookPageNumber)
        intent.putExtra("bookID", book.id)
        startActivity(intent)
        this.finish()
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