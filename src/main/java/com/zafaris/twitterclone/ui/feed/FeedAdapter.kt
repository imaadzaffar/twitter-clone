package com.zafaris.twitterclone.ui.feed

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zafaris.twitterclone.R
import com.zafaris.twitterclone.model.Tweet

class FeedAdapter(private val context: Context?, private val tweetsList: List<Tweet>) : RecyclerView.Adapter<FeedAdapter.FeedViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FeedViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_tweet, parent, false)
        return FeedViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FeedViewHolder, position: Int) {
        holder.usernameTextView.text = context?.resources?.getString(R.string.tweet_username, tweetsList[position].username)
        holder.messageTextView.text = tweetsList[position].message
    }

    override fun getItemCount(): Int = tweetsList.size

    inner class FeedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.textview_username)
        val messageTextView: TextView = itemView.findViewById(R.id.textview_message)
    }

}