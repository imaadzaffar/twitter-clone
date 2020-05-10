package com.zafaris.twitterclone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

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
            //TODO: Send tweet dialog
        }
    }

}
