package com.abanoub.marvel.main

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abanoub.marvel.R
import com.abanoub.marvel.data.model.Character
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.character_single_item.view.*


class CharactersAdapter(var list: ArrayList<Character>, var itemCallback: OnItemClick) :
    RecyclerView.Adapter<CharactersAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater =
            parent.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.character_single_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var item = list.get(position)
        var imageLink =
            item.getThumbnails()?.getPath() + "." + item.getThumbnails()?.getExtension()

        Log.e("onBindViewHolder: ", imageLink)
        holder.itemView.name.setText(item.getName())
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