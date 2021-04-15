package com.abanoub.marvel.detailed

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.abanoub.marvel.R
import com.abanoub.marvel.data.model.Character
import com.abanoub.marvel.data.model.CharacterData
import com.abanoub.marvel.utils.Utils
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_splash.view.*
import kotlinx.android.synthetic.main.character_single_item.view.*


class CharacterDataAdapter(var list: ArrayList<CharacterData>) :
    RecyclerView.Adapter<CharacterDataAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View =
            inflater.inflate(R.layout.comic_events_series_stories_single_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = list.get(position)

        holder.itemView.name.setText(item.getTitle())

        var thumbnail = item.getThumbnail()
        var imageLink = thumbnail?.getPath() + "." + thumbnail?.getExtension()

        Log.e("onBindViewHolder: ", imageLink)
        Glide.with(holder.itemView.context)
            .load(imageLink)
            .placeholder(R.drawable.image_placeholder)
            .into(holder.itemView.pic)
        holder.itemView.rootView.clipToOutline = true
    }
}