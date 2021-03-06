package com.abanoub.marvel.main

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.text.SpannableString
import android.text.style.BackgroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.abanoub.marvel.R
import com.abanoub.marvel.data.model.Character
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_single_item.view.*


class SearchAdapter(var list: ArrayList<Character>,var searchTxt:String, var itemCallback: OnItemClick) :
    RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.search_single_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = list.get(position)
        var imageLink =
            item.getThumbnails()?.getPath() + "." + item.getThumbnails()?.getExtension()

        Log.e("onBindViewHolder: ", imageLink)

        val str = SpannableString(item.getName())
        str.setSpan(BackgroundColorSpan(Color.RED), 0, item.getName()!!.length, 0)
        holder.itemView.name.setText(str)

        Glide.with(holder.itemView.context)
            .load(imageLink)
            .placeholder(R.drawable.image_placeholder)
            .into(holder.itemView.pic)

        holder.itemView.rootView.clipToOutline=true

        holder.itemView.setOnClickListener{itemCallback.onItemClicked(item)}
    }

    public interface OnItemClick {
        fun onItemClicked(character: Character)
    }
}