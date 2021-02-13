package com.tenorinho.moviedetail.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.tenorinho.moviedetail.R
import com.tenorinho.moviedetail.data.viewmodel.MovieViewModel
import com.tenorinho.moviedetail.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    //private var binding:ActivityMainBinding? = null
    //private val viewModel:MovieViewModel by lazy{ViewModelProvider.NewInstanceFactory().create(MovieViewModel::class.java)}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        //viewModel.getMovie()
        //viewModel.getSimilarMovies()
    }
    override fun onStart() {
        super.onStart()
        //binding?.viewModel = viewModel
    }
}