package com.example.appgithubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.appgithubuser.ui.detail.DetailActivity
import com.example.appgithubuser.R
import com.example.appgithubuser.data.api.response.User

class UserAdapter(private val listUser: List<User>) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {
    class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
        val tvUser: TextView = view.findViewById(R.id.tvUser)
        val profileUser: ImageView = view.findViewById(R.id.profileUser)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(LayoutInflater.from(viewGroup.context).inflate(R.layout.item_user, viewGroup, false))



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val list = listUser[position]
        holder.tvUser.text = list.login
        Glide.with(holder.itemView.context)
            .load(list.avatar_url)
            .into(holder.profileUser)

        holder.itemView.setOnClickListener {
            val moveIntent = Intent(holder.itemView.context, DetailActivity::class.java)
            moveIntent.putExtra("username",list.login)
            moveIntent.putExtra("avatar_url", list.login)
            holder.itemView.context.startActivity(moveIntent)
        }
    }


    override fun getItemCount(): Int = listUser.size

}