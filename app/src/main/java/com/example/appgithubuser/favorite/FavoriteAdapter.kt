package com.example.appgithubuser.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgithubuser.data.local.database.Favorite
import com.example.appgithubuser.databinding.ItemUserBinding
import com.example.appgithubuser.ui.detail.DetailActivity

class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private var listFavorite = ArrayList<Favorite>()

    class ViewHolder(val bindingFavorite: ItemUserBinding) :
        RecyclerView.ViewHolder(bindingFavorite.root)

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemUserBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listFavorite[position]
        holder.bindingFavorite.tvUser.text = list.username
        Glide.with(holder.itemView.context)
            .load(list.avatar)
            .into(holder.bindingFavorite.profileUser)

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, DetailActivity::class.java)
            moveIntent.putExtra("username", list.username)
            moveIntent.putExtra("avatar_url", list.avatar)
            holder.itemView.context.startActivity(moveIntent)
        }
    }

    override fun getItemCount(): Int = listFavorite.size

}