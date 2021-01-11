package com.techand.news.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.techand.news.R
import com.techand.news.data.model.Article
import com.techand.news.ui.home.HomeAdapter.DataViewHolder
import kotlinx.android.synthetic.main.item_layout.view.*


class HomeAdapter(private val users: ArrayList<Article>) : RecyclerView.Adapter<DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(user: Article) {
            itemView.apply {
                textViewTitle.text = user.title
                date.text = user.publishedAt
                textViewDescription.text = user.description

                val options: RequestOptions = RequestOptions()
                    .placeholder(R.drawable.ic_baseline_image_search_24)
                    .error(R.drawable.ic_baseline_image_not_supported_24)
                Glide.with(imageViewAvatar.context)
                    .load(user.urlToImage)
                    .apply(options)
                    .into(imageViewAvatar)


            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout,
                parent,
                false
            )
        )

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(users[position])
    }

    fun addUsers(users: List<Article>) {
        this.users.apply {
            clear()
            addAll(users)
        }

    }
}