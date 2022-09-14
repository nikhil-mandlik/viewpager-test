package com.nikhil.here.viewpager_test.ui.fragment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.nikhil.here.viewpager_test.databinding.FragmentInteractiveLiveStreamBinding
import com.nikhil.here.viewpager_test.ui.MainActivity
import com.nikhil.here.viewpager_test.ui.MainViewModel
import com.nikhil.here.viewpager_test.ui.utility.Constants
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.launch


class InteractiveLiveStreamFragment : Fragment() {

    private var _binding: FragmentInteractiveLiveStreamBinding? = null
    private val binding get() = _binding!!
    private var livestreamId: String = "1"


    private val mainViewModel: MainViewModel by activityViewModels()

    companion object {
        fun getFragment(liveStreamId: String) = InteractiveLiveStreamFragment()
            .apply {
                arguments = bundleOf(
                    Constants.INTERACTIVE_LIVESTREAM_ID to liveStreamId
                )
            }

        private const val TAG = "Interactive"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInteractiveLiveStreamBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.i(TAG, "onViewCreated: is called ${this.hashCode()}")
        parseArguments()
        initFlow()
    }


    private fun initFlow() {
//        viewLifecycleOwner.lifecycleScope.launch {
//            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.RESUMED) {
//                mainViewModel.downStreamEvent
//                    .onCompletion {
//                        Log.i(TAG, "initFlow: onCompletion is called")
//                    }
//                    .collect {
//                        //Log.i(TAG, "initFlow: collected value $it")
//                    }
//            }
//        }
//
//        lifecycleScope.launchWhenStarted {
//            mainViewModel.downStreamEvent
//                .onCompletion {
//                    Log.i(TAG, "initFlow: onCompletion is called")
//                }
//                .collect {
//                    Log.i(TAG, "initFlow: collected value $it")
//                }
//        }
    }

    private fun parseArguments() {
        livestreamId = arguments?.getString(Constants.INTERACTIVE_LIVESTREAM_ID) ?: ""
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