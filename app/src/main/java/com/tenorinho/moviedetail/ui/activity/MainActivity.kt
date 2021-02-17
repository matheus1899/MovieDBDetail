package com.tenorinho.moviedetail.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tenorinho.moviedetail.R
import com.tenorinho.moviedetail.data.model.Movie
import com.tenorinho.moviedetail.data.viewmodel.MainActivityViewModel
import com.tenorinho.moviedetail.databinding.ActivityMainBinding
import com.tenorinho.moviedetail.ui.adapter.SimilarMoviesAdapter

class MainActivity : AppCompatActivity() {
    private var binding: ActivityMainBinding? = null
    private lateinit var viewModel:MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        createViewModel()
        binding?.viewModel = viewModel
        binding?.lifecycleOwner = this
        val adapter = SimilarMoviesAdapter(viewModel.similarMovies.value)
        binding?.recyclerView?.layoutManager = LinearLayoutManager(this)
        binding?.recyclerView?.adapter = adapter
        viewModel.error.observe(this, Observer { Toast.makeText(this,it.message, Toast.LENGTH_LONG).show() })
        viewModel.similarMovies.observe(this, Observer { adapter.updateList(viewModel.similarMovies.value) })
    }
    fun createViewModel(){
        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
    }
}