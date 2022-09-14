package com.nikhil.here.viewpager_test.ui.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import com.nikhil.here.viewpager_test.databinding.FragmentObsLiveStreamBinding
import com.nikhil.here.viewpager_test.ui.utility.Constants


class ObsLiveStreamFragment : Fragment() {
    private var _binding: FragmentObsLiveStreamBinding? = null
    private val binding get() = _binding!!
    private var livestreamId: String = "2"

    companion object {
        fun getFragment(obsLiveStreamId: String) = ObsLiveStreamFragment()
            .apply {
                arguments = bundleOf(
                    Constants.OBS_LIVESTREAM_ID to obsLiveStreamId
                )
            }
        private const val TAG = "Interactive"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentObsLiveStreamBinding.inflate(layoutInflater, container, false)
        Log.i(TAG, "onCreateView: is called ${this.hashCode()}")
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: is called ${this.hashCode()}")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: is called ${this.hashCode()}")
    }

    override fun onStop() {
        Log.i(TAG, "onStop: is called ${this.hashCode()}")
        super.onStop()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView: is called ${this.hashCode()}")
        _binding = null
    }

    override fun onAttach(context: Context) {
        Log.i(TAG, "onAttach: is called ${this.hashCode()}")
        super.onAttach(context)
    }

    override fun onPause() {
        Log.i(TAG, "onPause: is called ${this.hashCode()}")
        super.onPause()
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach: is called ${this.hashCode()}")
    }
}