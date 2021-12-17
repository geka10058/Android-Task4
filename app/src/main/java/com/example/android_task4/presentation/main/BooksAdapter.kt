package com.example.android_task4.presentation.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task4.R
import com.example.android_task4.data.Book
import com.example.android_task4.databinding.BookItemBinding

class BooksAdapter(
    private val callback: Callbacks
) : RecyclerView.Adapter<BooksAdapter.ViewHolder>() {

    private val allBooks = ArrayList<Book>()

    override fun getItemCount(): Int = allBooks.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val book = allBooks[position]
        with(holder) {
            titleField.text = book.bookTitle
            authorField.text = book.bookAuthor
            pageNumberField.text = book.bookPageNumber
            deleteItem.setOnClickListener {
                callback.onBookDeleteIconClick(book)
            }
            upgradeItem.setOnClickListener {
                callback.onBookClick(book)
            }
        }
    }

    fun updateList(newList: List<Book>) {
        allBooks.clear()
        allBooks.addAll(newList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = BookItemBinding.bind(itemView)

        val titleField = binding.titleField
        val authorField = binding.authorField
        val pageNumberField = binding.pageNumberField
        val deleteItem = binding.deleteButton
        val upgradeItem = binding.upgradeButton
    }

    interface Callbacks {
        fun onBookClick(book: Book)
        fun onBookDeleteIconClick(book: Book)
    }
}
