package com.nikhil.here.viewpager_test.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.nikhil.here.viewpager_test.databinding.FragmentUpcomingLiveEventsBinding
import com.nikhil.here.viewpager_test.ui.utility.Constants


class UpcomingLiveEventsFragment : Fragment() {
    private var _binding: FragmentUpcomingLiveEventsBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun getFragment(pageId: String) = UpcomingLiveEventsFragment()
            .apply {
                arguments = bundleOf(
                    Constants.UPCOMING_LIVE_EVENTS_PAGE_ID to pageId
                )
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentUpcomingLiveEventsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}