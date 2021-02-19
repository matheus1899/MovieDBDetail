package com.tenorinho.moviedetail.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenorinho.moviedetail.App
import com.tenorinho.moviedetail.R
import com.tenorinho.moviedetail.data.viewmodel.MainActivityViewModel
import com.tenorinho.moviedetail.data.viewmodel.MainActivityViewModelFactory
import com.tenorinho.moviedetail.databinding.ActivityMainBinding
import com.tenorinho.moviedetail.ui.adapter.ListMoviesAdapter

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel:MainActivityViewModel
    private val adapter by lazy{ ListMoviesAdapter(viewModel.similarMovies.value, viewModel) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        init()
    }
    private fun init(){
        createViewModel()
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.adapter = adapter
        viewModel.error.observe(this, Observer { Toast.makeText(this,it.message, Toast.LENGTH_LONG).show() })
        viewModel.similarMovies.observe(this, Observer { adapter.updateList(viewModel.similarMovies.value) })
    }
    private fun createViewModel(){
        val app = application as App
        val factory = MainActivityViewModelFactory(app.genrerRepository, app.movieRepository)
        viewModel = ViewModelProvider(this, factory).get(MainActivityViewModel::class.java)
    }
}