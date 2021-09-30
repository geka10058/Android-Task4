package com.example.android_task4

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task4.databinding.BookItemBinding
import com.example.android_task4.databinding.DbItemBinding

class BookRVAdapter(val context: Context):RecyclerView.Adapter<BookRVAdapter.ViewHolder>() {
    private val allBooks = emptyList<Book>()

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        private val binding = BookItemBinding.bind(itemView)
        fun bind(book: Book) = with(binding){
            titleField.text = book.bookTitle
            authorField.text = book.bookAuthor
            releaseYearField.text = book.bookReleaseYear

            upgradeButton.setOnClickListener {
                Toast.makeText(
                    context,
                    "Зесь олжно было быть переименование",
                    Toast.LENGTH_SHORT
                ).show()
            }

            deleteButton.setOnClickListener {

                Toast.makeText(
                    context,
                    "Зесь олжно было быть удаление",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        val deleteItem = binding.deleteButton
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.book_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(allBooks[position])
        // если не работает добавление данных, то видео  34:58
        // если не работают кнопкки внутри, то 35:33
        holder.deleteItem.setOnClickListener {
            Toast.makeText(
                context,
                "Зесь ДОЛЖНО было быть удаление",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun getItemCount(): Int {
        return allBooks.size
    }

    fun updateList(newList: List<Book>){
        /*allBooks.clear()
        allBooks.addAll(newList)*/
        notifyDataSetChanged()
    }
}

