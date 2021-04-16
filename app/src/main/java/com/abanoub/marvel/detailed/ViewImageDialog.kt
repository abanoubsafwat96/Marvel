package com.abanoub.marvel.detailed

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import com.abanoub.marvel.R
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.dialog_view_image.*

class ViewImageDialog(context: Context, var imagePath: String) : Dialog(context, android.R.style.Theme_Black_NoTitleBar) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_view_image)

        window?.apply {
            setCancelable(true)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        }//apply

        if (imagePath != null)
            Glide.with(context)
                .load(imagePath)
                .placeholder(R.drawable.image_placeholder)
                .into(imageView)
    }
}