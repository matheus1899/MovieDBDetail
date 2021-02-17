package com.tenorinho.moviedetail.util

import android.net.Uri
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter

class BindAdapter{
    companion object{
        //To use data binding annotations in Kotlin, apply the
        //'kotlin-kapt' plugin in your module's build.gradle
        @BindingAdapter("onClick")
        @JvmStatic fun onClick(view: View, onClick:() -> Unit){
            view.setOnClickListener{
                onClick()
            }
        }
        @BindingAdapter("imgSource")
        @JvmStatic fun setImageResource(img: ImageView, res:Int){
            img.setImageResource(res)
        }
    }
}