package com.zafaris.twitterclone.ui.feed

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.parse.ParseObject
import com.parse.ParseQuery
import com.zafaris.twitterclone.R
import com.zafaris.twitterclone.model.Tweet
import kotlinx.android.synthetic.main.fragment_feed.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FeedFragment : Fragment() {

    private lateinit var feedAdapter: FeedAdapter
    private val tweetsList: MutableList<Tweet> = ArrayList()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Feed"

        val query = ParseQuery.getQuery<ParseObject>("Tweet")
        //TODO: query.whereContainedIn("username", friendsUsernames)
        query.orderByDescending("createdAt")
        query.findInBackground { tweets, parseException ->
            if (tweets.size > 0 && parseException == null) {
                Log.d("FeedFragment", "Successfully retrieved tweets")

                for (tweet in tweets) {
                    val username = tweet.getString("username")!!
                    val message = tweet.getString("message")!!
                    Log.d("Tweet", "username: $username, message: $message")
                    tweetsList.add(Tweet(username, message))
                }

                feedAdapter = FeedAdapter(context, tweetsList)
                recyclerview_tweets.adapter = feedAdapter
                recyclerview_tweets.layoutManager = LinearLayoutManager(activity)

            } else {
                Log.d("FeedFragment", parseException.message.toString())
                Toast.makeText(activity, "Error retrieving tweets...", Toast.LENGTH_SHORT).show()
            }
        }

    }
}
