package com.tenorinho.moviedetail.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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
        @BindingAdapter("imgSource")
        @JvmStatic fun setImageUrl(img: ImageView, url:String?){
            if(url != null || !url.isNullOrBlank()){
                Glide
                    .with(img.context)
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(img)
            }
        }
    }
}