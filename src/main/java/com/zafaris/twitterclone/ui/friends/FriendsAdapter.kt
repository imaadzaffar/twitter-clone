package com.zafaris.twitterclone.ui.friends

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zafaris.twitterclone.R
import com.zafaris.twitterclone.model.User
import kotlinx.android.synthetic.main.item_friend.view.*

class FriendsAdapter(private val context: Context?, private val usersList: List<User>) : RecyclerView.Adapter<FriendsAdapter.FriendsViewHolder>() {
    private var listener: ((User) -> Unit)? = null

    fun setOnItemClickListener(listener: ((User) -> Unit)) {
        this.listener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FriendsAdapter.FriendsViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_friend, parent, false)
        return FriendsViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FriendsAdapter.FriendsViewHolder, position: Int) {
        holder.usernameTextView.text = context?.getString(R.string.twitter_username, usersList[position].username)
        if (usersList[position].isFollowing) {
            holder.followImageButton.setImageDrawable(context?.getDrawable(R.drawable.ic_following))
        } else {
            holder.followImageButton.setImageDrawable(context?.getDrawable(R.drawable.ic_follow))
        }
    }

    override fun getItemCount(): Int = usersList.size

    inner class FriendsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val usernameTextView: TextView = itemView.findViewById(R.id.textview_friend_username)
        val followImageButton: ImageButton = itemView.findViewById(R.id.imagebutton_friend_follow)

        init {
            itemView.imagebutton_friend_follow.setOnClickListener { listener?.invoke(usersList[adapterPosition]) }
        }
    }

}