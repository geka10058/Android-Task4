package com.example.android_task4


import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_task4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var viewModel: BookViewModel
    lateinit var booksRecycler: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        booksRecycler = binding.recycler
        booksRecycler.layoutManager = LinearLayoutManager(this)

        val bookRVAdapter = BookRVAdapter(this)
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

    /*@InternalCoroutinesApi
    lateinit var bookviewModel: BookViewModel
    //private val adapter = BookRVAdapter(context = Context)*/


   /* override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentRV) as NavHostFragment
        val navController: NavController = navHostFragment.findNavController()

        setupActionBarWithNavController(navController)


    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentRV)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }*/

    /*override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }*/

   /* override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }*/

    /*override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }*/
}