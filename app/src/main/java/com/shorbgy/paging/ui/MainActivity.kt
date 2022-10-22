package com.shorbgy.paging.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.shorbgy.paging.adapters.CatsAdapter
import com.shorbgy.paging.databinding.ActivityMainBinding
import com.shorbgy.paging.network.NetworkViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: NetworkViewModel by viewModels()
    private val adapter by lazy { CatsAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initAdapter()
        lifecycleScope.launch {
            viewModel.cats.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun initAdapter(){
        binding.catsRv.adapter = adapter
    }
}