package com.example.android_task4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.preference.PreferenceManager
import com.example.android_task4.databinding.ActivityAddUpgradeBinding


class AddUpgradeActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel
    private lateinit var binding: ActivityAddUpgradeBinding

    var bookID = -1
    var title = ""
    var author = ""
    var pagesNumber = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpgradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(BookViewModel::class.java)

        val bookType = intent.getStringExtra("bookType")
        if (bookType.equals("Edit")) {
            val bookTitle = intent.getStringExtra("bookTitle")
            val bookAuthor = intent.getStringExtra("bookAuthor")
            val bookYear = intent.getStringExtra("bookNumber")
            bookID = intent.getIntExtra("bookID", -1)
            binding.buttonAddUpgrade.setText(R.string.upgrade)
            title = binding.editBookTitle.setText(bookTitle).toString()
            author = binding.editBookAuthor.setText(bookAuthor).toString()
            pagesNumber = binding.editBookPageNumber.setText(bookYear).toString()
        } else {
            binding.buttonAddUpgrade.setText(R.string.add)
        }

        binding.buttonAddUpgrade.setOnClickListener {

            title = binding.editBookTitle.text.toString()
            author = binding.editBookAuthor.text.toString()
            pagesNumber = binding.editBookPageNumber.text.toString()

            if(bookType.equals("Edit")){
                if(binding.editBookTitle.text.isNotEmpty() || binding.editBookAuthor.text.isNotEmpty() || binding.editBookPageNumber.text.isNotEmpty()){
                    val updatedBook = Book(title,author,pagesNumber)
                    updatedBook.id = bookID
                    bookViewModel.upgradeBook(updatedBook)
                    Toast.makeText(
                        this,
                        R.string.data_successfully_upgraded,
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    this.finish()
                }
            } else {
                if (binding.editBookTitle.text.isNotEmpty() || binding.editBookAuthor.text.isNotEmpty() || binding.editBookPageNumber.text.isNotEmpty()) {
                    title = binding.editBookTitle.text.toString()
                    author = binding.editBookAuthor.text.toString()
                    pagesNumber = binding.editBookPageNumber.text.toString()
                    bookViewModel.addBook(Book(title,author,pagesNumber))
                    Toast.makeText(
                        this,
                        R.string.data_successfully_added,
                        Toast.LENGTH_SHORT
                    ).show()
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    this.finish()
                } else Toast.makeText(this,
                    R.string.fill_in_all_fields,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }

        binding.buttonClose.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

    override fun onBackPressed() {
        startActivity(Intent(applicationContext, MainActivity::class.java))
        this.finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        if (item.itemId == R.id.action_settings){
            val intent = Intent(this@AddUpgradeActivity,SettingsActivity::class.java)
            startActivity(intent)
            Toast.makeText(
                this,
                "Settings run",
                Toast.LENGTH_SHORT
            ).show()
        }
        return true
    }

    override fun onResume() {
        super.onResume()
    }
}
