package com.example.android_task4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.persistableBundleOf
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task4.databinding.BookItemBinding

class BookRVAdapter(
    val context: Context,
    val bookClickInterface: BookClickInterface,
    val bookClickDeleteInterface: BookClickDeleteInterface
    ):RecyclerView.Adapter<BookRVAdapter.ViewHolder>() {
    private val allBooks = ArrayList<Book>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = BookItemBinding.bind(itemView)

        val titleField = binding.titleField
        val authorField = binding.authorField
        val releaseYearField = binding.releaseYearField
        val deleteItem = binding.deleteButton
        val upgradeItem = binding.upgradeButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titleField.setText(allBooks.get(position).bookTitle)
        holder.authorField.setText(allBooks.get(position).bookAuthor)
        holder.releaseYearField.setText(allBooks.get(position).bookReleaseYear)
        holder.deleteItem.setOnClickListener {
            bookClickDeleteInterface.onBookDeleteIconClick(allBooks.get(position))
        }

        holder.upgradeItem.setOnClickListener {
            bookClickInterface.onBookClick(allBooks.get(position))
        }
    }

    override fun getItemCount(): Int {
        return allBooks.size
    }

    fun updateList(newList: List<Book>){
        allBooks.clear()
        allBooks.addAll(newList)
        notifyDataSetChanged()
    }
}

interface BookClickInterface{
    fun onBookClick(book: Book)
}

interface BookClickDeleteInterface{
    fun onBookDeleteIconClick(book: Book)
}

