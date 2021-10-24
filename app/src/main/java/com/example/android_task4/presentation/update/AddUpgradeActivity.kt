package com.example.android_task4.presentation.update

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.android_task4.R
import com.example.android_task4.data.Book
import com.example.android_task4.databinding.ActivityAddUpgradeBinding
import com.example.android_task4.presentation.BookViewModel
import com.example.android_task4.presentation.settings.SettingsActivity
import dagger.hilt.android.AndroidEntryPoint
import java.math.BigInteger
import kotlin.LazyThreadSafetyMode.NONE

@AndroidEntryPoint
class AddUpgradeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddUpgradeBinding

    private val bookViewModel: BookViewModel by viewModels()

    private val bookID: Long by lazy(NONE) {
        intent.getLongExtra("bookID", -1L)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpgradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bookType = intent.getStringExtra("bookType")
        if (bookType.equals("Edit")) {
            binding.buttonAddUpgrade.setText(R.string.upgrade)
            initFieldsFromIntent(intent)
        } else {
            binding.buttonAddUpgrade.setText(R.string.add)
        }

        binding.buttonAddUpgrade.setOnClickListener {
            val book = assembleBook(id = bookID)
            val errorMessage = validateBook(book)
            if (errorMessage != null) {
                Toast.makeText(this, "Validation error: $errorMessage", LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            // Note: ViewModel operations are not synchronous, so it's better
            // to wait for its completion and only then execute navigation logic
            when (bookType) {
                "Edit" -> bookViewModel.upgradeBook(book)
                else -> bookViewModel.addBook(book)
            }

            Toast.makeText(this, R.string.data_successfully_added, LENGTH_SHORT)
                .show()
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem):Boolean {
        if (item.itemId == R.id.action_settings) {
            val intent = Intent(this@AddUpgradeActivity, SettingsActivity::class.java)
            startActivity(intent)
            Toast.makeText(this, "Settings run", LENGTH_SHORT).show()
        }
        return true
    }

    private fun initFieldsFromIntent(intent: Intent) {
        val bookTitle = intent.getStringExtra("bookTitle")
        val bookAuthor = intent.getStringExtra("bookAuthor")
        val bookYear = intent.getStringExtra("bookNumber")
        binding.editBookTitle.setText(bookTitle)
        binding.editBookAuthor.setText(bookAuthor)
        binding.editBookPageNumber.setText(bookYear)
    }

    private fun assembleBook(id: Long?): Book {
        val book = with(binding) {
            Book(
                bookTitle = editBookTitle.text.toString(),
                bookAuthor = editBookAuthor.text.toString(),
                bookPageNumber = editBookPageNumber.text.toString(),
            )
        }
        if (id != null) {
            book.id = id
        }
        return book
    }

    // Note: This logic is usually placed in ViewModel
    private fun validateBook(book: Book): String? {
        val pagesNumber = book.bookPageNumber.toBigIntegerOrNull()
        return when {
            book.bookTitle.isBlank() -> getString(R.string.validation_error_blank_title)
            book.bookAuthor.isBlank() -> getString(R.string.validation_error_blank_author)
            book.bookPageNumber.isBlank() -> getString(R.string.validation_error_blank_number_of_pages)
            pagesNumber == null || pagesNumber <= BigInteger.ZERO -> getString(R.string.validation_error_number_of_pages_is_not_a_number)
            else -> null
        }
    }
}
