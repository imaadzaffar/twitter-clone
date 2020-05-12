package com.zafaris.twitterclone.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.parse.ParseObject
import com.parse.ParseUser
import com.zafaris.twitterclone.R


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.title = "Home"

        view.findViewById<Button>(R.id.button_feed).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_FeedFragment)
        }

        view.findViewById<Button>(R.id.button_friends).setOnClickListener {
            findNavController().navigate(R.id.action_HomeFragment_to_FriendsFragment)
        }

        view.findViewById<Button>(R.id.button_tweet).setOnClickListener {
            showSendTweetDialog()
        }
    }

    private fun showSendTweetDialog() {
        val editText = EditText(context)

        AlertDialog.Builder(context!!, R.style.Theme_AppCompat_Light_Dialog_Alert)
            .setTitle("Send Tweet")
            .setView(editText)
            .setPositiveButton("Send") { _, _ ->
                if (editText.text.isNotEmpty()) {

                    val tweet = ParseObject("Tweet")
                    tweet.put("username", ParseUser.getCurrentUser().username)
                    tweet.put("message", editText.text.toString())
                    tweet.saveInBackground {
                        Toast.makeText(activity, "Successfully sent Tweet", Toast.LENGTH_SHORT).show()
                    }

                } else {
                    Toast.makeText(activity, "Enter a message", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.dismiss() }
            .create()
            .show()
    }

}
