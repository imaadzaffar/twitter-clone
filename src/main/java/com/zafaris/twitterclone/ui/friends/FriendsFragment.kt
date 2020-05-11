package com.zafaris.twitterclone.ui.friends

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.parse.ParseObject
import com.parse.ParseQuery
import com.parse.ParseUser
import com.zafaris.twitterclone.R
import com.zafaris.twitterclone.model.User
import kotlinx.android.synthetic.main.fragment_friends.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FriendsFragment : Fragment() {

    private lateinit var friendsAdapter: FriendsAdapter
    private val usersList: MutableList<User> = ArrayList()
    private lateinit var friendsList: MutableList<String>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_friends, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Friends"
        getFriends()
    }

    private fun getFriends() {
        val friendsQuery = ParseQuery.getQuery<ParseObject>("_User")
        friendsQuery.whereEqualTo("username", ParseUser.getCurrentUser().username)
        friendsQuery.findInBackground { friends, friendsException ->
            if (friends.size > 0 && friendsException == null) {
                Log.d("Friends List", friends[0].getList<String>("friends").toString())
                friendsList = friends[0].getList("friends")!!
                getUsers()
            } else {
                Log.d("FriendsFragment", friendsException.message.toString())
                Toast.makeText(activity, "Error retrieving friends...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getUsers() {
        val usersQuery = ParseQuery.getQuery<ParseObject>("_User")
        usersQuery.whereNotEqualTo("username", ParseUser.getCurrentUser().username)
        usersQuery.findInBackground { users, usersException ->
            if (users.size > 0 && usersException == null) {

                for (user in users) {
                    val username = user.getString("username")!!
                    if (username in friendsList) {
                        usersList.add(User(username, true))
                    } else {
                        usersList.add(User(username, false))
                    }
                }

                friendsAdapter = FriendsAdapter(context, usersList)
                friendsAdapter.setOnItemClickListener { user -> followButtonClick(user) }
                recyclerview_friends.adapter = friendsAdapter
                recyclerview_friends.layoutManager = LinearLayoutManager(activity)

            } else {
                Log.d("FriendsFragment", usersException.message.toString())
                Toast.makeText(activity, "Error retrieving users...", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun followButtonClick(user: User) {
        if (user.isFollowing) {
            friendsList.remove(user.username)
            usersList[usersList.indexOf(user)] = User(user.username, false)
        } else {
            friendsList.add(user.username)
            usersList[usersList.indexOf(user)] = User(user.username, true)
        }
        ParseUser.getCurrentUser().put("friends", friendsList)
        ParseUser.getCurrentUser().saveInBackground {
            friendsAdapter.notifyDataSetChanged()
        }
    }

}
