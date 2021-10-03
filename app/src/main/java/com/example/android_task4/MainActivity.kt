package com.example.android_task4


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
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

        val bookRVAdapter = BookRVAdapter(this,this,this)
        booksRecycler.adapter = bookRVAdapter
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(BookViewModel::class.java)
        viewModel.allBooks.observe(this, {
                list -> list?.let{
                    bookRVAdapter.updateList(it)
                }
        })

        binding.addButton.setOnClickListener {
            val intent = Intent(this@MainActivity,AddUpgradeActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }

    override fun onBookClick(book: Book) {
        val intent = Intent(this@MainActivity,AddUpgradeActivity::class.java)
        intent.putExtra("bookType", "Edit")
        intent.putExtra("bookTitle", book.bookTitle)
        intent.putExtra("bookAuthor", book.bookAuthor)
        intent.putExtra("bookYear", book.bookReleaseYear)
        intent.putExtra("bookID", book.id)
        startActivity(intent)
        this.finish()
    }

    override fun onBookDeleteIconClick(book: Book) {
        viewModel.deleteBook(book)
        Toast.makeText(this,"${book.bookTitle} Deleted", Toast.LENGTH_SHORT).show()

    }
}