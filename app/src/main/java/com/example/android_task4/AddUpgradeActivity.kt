package com.example.android_task4

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.android_task4.databinding.ActivityAddUpgradeBinding


class AddUpgradeActivity : AppCompatActivity() {
    private lateinit var bookViewModel: BookViewModel
    private lateinit var binding: ActivityAddUpgradeBinding

    //test<
    var bookID = -1
    var title = ""
    var author = ""
    var pagesNumber = ""
    //test>

    /*var bookID = -1
    lateinit var title: EditText
    lateinit var author: EditText
    lateinit var releaseYear: EditText*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddUpgradeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bookViewModel = ViewModelProvider(
            this, ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(BookViewModel::class.java)

        /*val bookType = intent.getStringExtra("bookType")
        if (bookType.equals("Edit")) {
            val bookTitle = intent.getStringExtra("bookTitle")
            val bookAuthor = intent.getStringExtra("bookAuthor")
            val bookYear = intent.getStringExtra("bookYear")
            bookID = intent.getIntExtra("bookID", -1)
            binding.buttonAddUpgrade.setText(R.string.upgrade)
            title.setText(bookTitle)
            author.setText(bookAuthor)
            releaseYear.setText(bookYear)
        } else {
            binding.buttonAddUpgrade.setText(R.string.add)
        }*/

        //test<
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

        /*if (binding.editBookTitle.text.isEmpty() || binding.editBookAuthor.text.isEmpty() || binding.editBookReleaseYear.text.isEmpty()) {
            binding.buttonAddUpgrade.setText(R.string.add)
        } else binding.buttonAddUpgrade.setText(R.string.upgrade)*/

        //test>

        binding.buttonAddUpgrade.setOnClickListener {
           /* val bookTitle = title.text.toString()
            val bookAuthor = author.text.toString()
            val bookYear = releaseYear.text.toString()

            if (bookType.equals("Edit")) {
                if (bookTitle.isNotEmpty() && bookAuthor.isNotEmpty() && bookYear.isNotEmpty()) {
                    val updateBook = Book(bookTitle, bookAuthor, bookYear)
                    updateBook.id = bookID
                    bookViewModel.upgradeBook(updateBook)
                    Toast.makeText(
                        this,
                        R.string.data_successfully_upgraded,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                if (bookTitle.isNotEmpty() && bookAuthor.isNotEmpty() && bookYear.isNotEmpty()) {
                    bookViewModel.addBook(Book(bookTitle, bookAuthor, bookYear))
                    Toast.makeText(
                        this,
                        R.string.data_successfully_added,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            Toast.makeText(
                this,
                R.string.data_successfully_added,
                Toast.LENGTH_SHORT
            ).show()*/


            //test<
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








            //test>



        }

        binding.buttonClose.setOnClickListener {
            startActivity(Intent(applicationContext, MainActivity::class.java))
            this.finish()
        }
    }

    /*private fun inputCheck(title: String, author: String, releaseYear: String): Boolean {
        return !(title.isBlank() || author.isBlank() || releaseYear.isBlank())
    }*/
}
/*if(inputCheck(
                binding.editBookTitle.text.toString(),
                binding.editBookAuthor.text.toString(),
                binding.editBookReleaseYear.text.toString()
        )) {
            binding.buttonAddUpgrade.setText("UPGRADEEEE")
            *//*title = binding.editBookTitle.text.toString()
            author = binding.editBookAuthor.text.toString()
            releaseYear = binding.editBookReleaseYear.text.toString()*//*

        } else binding.buttonAddUpgrade.setText("ADDD")*/


/*binding.buttonAddUpgrade.setOnClickListener {
    title = binding.editBookTitle.text.toString()
    author = binding.editBookAuthor.text.toString()
    releaseYear = binding.editBookReleaseYear.text.toString()
    val book = Book(title,author,releaseYear)
    bookViewModel.addBook(Book(title,author,releaseYear))

    if(inputCheck(
            binding.editBookTitle.text.toString(),
            binding.editBookAuthor.text.toString(),
            binding.editBookReleaseYear.text.toString()
        )) {
        Toast.makeText(applicationContext,R.string.data_successfully_added, Toast.LENGTH_SHORT).show()
        startActivity(Intent(applicationContext,MainActivity::class.java))
        this.finish()
    } else {

        Toast.makeText(applicationContext,R.string.fill_in_all_fields, Toast.LENGTH_SHORT).show()
    }

}*/