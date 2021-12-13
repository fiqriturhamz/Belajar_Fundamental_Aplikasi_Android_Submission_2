package com.example.fiqri_bfaa_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fiqri_bfaa_2.databinding.ActivityMainBinding
import com.example.fiqri_bfaa_2.model.User
import com.example.fiqri_bfaa_2.ui.MainViewModel
import com.example.fiqri_bfaa_2.ui.UserAdapter
import com.example.fiqri_bfaa_2.ui.detail.DetailUserActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
        binding.apply {
            rvUser.layoutManager = LinearLayoutManager(this@MainActivity)
            //    rvUser.adapter = adapter
            rvUser.setHasFixedSize(true)
            btnSearch.setOnClickListener {
                searchUser()
            }
            etQuery.setOnKeyListener { _, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false

            }
        }
        viewModel.getSearchUsers().observe(this, {
            if (it != null) {

                adapter = UserAdapter(it)
                binding.rvUser.adapter = adapter
                adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback {
                    override fun onItemClicked(data: User) {
                        Intent(this@MainActivity, DetailUserActivity::class.java).also {
                            it.putExtra(DetailUserActivity.EXTRA_USERNAME, data.login)
                            startActivity(it)
                        }
                    }


                })

                showLoading(false)
            }
        })

    }

    private fun searchUser() {
        binding.apply {
            val query = etQuery.text.toString()
            if (query.isEmpty()) return
            showLoading(true)
            viewModel.setSearchUsers(query)
        }
    }

    private fun showLoading(state: Boolean) {
        if (state) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}