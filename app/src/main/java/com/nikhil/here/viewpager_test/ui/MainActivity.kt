package com.nikhil.here.viewpager_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_DRAGGING
import androidx.viewpager2.widget.ViewPager2.SCROLL_STATE_SETTLING
import com.nikhil.here.viewpager_test.databinding.ActivityMainBinding
import com.nikhil.here.viewpager_test.ui.adapter.LiveFeedAdapter
import com.nikhil.here.viewpager_test.ui.sideeffects.MainActivitySideEffect
import com.nikhil.here.viewpager_test.ui.state.MainActivityState
import com.nikhil.here.viewpager_test.ui.utility.Constants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.observe

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var liveFeedAdapter: LiveFeedAdapter

    private val mainViewModel by viewModels<MainViewModel>()

    companion object {
        private const val TAG = "MainActivity_nikhil"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupEdgeToEdgeDisplay()
        initLiveFeed()
        initObservers()
        initListeners()
    }

    private fun initListeners() {
        binding.btnAddThreeInteractive.setOnClickListener {
            Log.i(TAG, "initListeners: Adding Three Interactive items")
            mainViewModel.addThreeLiveFeedItems()
        }

        binding.btnRemoveCurrent.setOnClickListener {
            Log.i(TAG, "initListeners: removing 0th item")
            mainViewModel.removeCurrent()
        }
    }

    private fun initObservers() {
        mainViewModel.observe(
            state = ::render,
            sideEffect = ::handleSideEffect,
            lifecycleOwner = this
        )
    }

    private fun render(mainActivityState: MainActivityState) {
        liveFeedAdapter.updateLiveFeed(mainActivityState.liveFeed)
    }

    private fun handleSideEffect(state: MainActivitySideEffect) {

    }

    private fun initLiveFeed() {
        liveFeedAdapter = LiveFeedAdapter(supportFragmentManager, this)
        with(binding.vpLiveFeed) {
            adapter = liveFeedAdapter
            registerOnPageChangeCallback(LiveFeedPageChangeCallBack())
            adapter?.registerAdapterDataObserver(LiveFeedAdapterDataObserver())
        }
    }


    inner class LiveFeedPageChangeCallBack : ViewPager2.OnPageChangeCallback() {
        override fun onPageScrollStateChanged(state: Int) {
            Log.i(TAG, "onPageScrollStateChanged: state : ${getScrollState(state)}")
            super.onPageScrollStateChanged(state)
        }

        override fun onPageScrolled(
            position: Int,
            positionOffset: Float,
            positionOffsetPixels: Int
        ) {
            Log.i(
                TAG,
                "onPageScrolled: position : $position positionOffset $positionOffset positionOffsetPixels : $positionOffsetPixels"
            )
            super.onPageScrolled(position, positionOffset, positionOffsetPixels)
        }

        override fun onPageSelected(position: Int) {
            Log.i(TAG, "onPageSelected: position : $position")
            super.onPageSelected(position)
        }
    }

    inner class LiveFeedAdapterDataObserver : RecyclerView.AdapterDataObserver() {
        override fun onChanged() {
            super.onChanged()
            Log.i(TAG, "onChanged: ")
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int) {
            super.onItemRangeChanged(positionStart, itemCount)
            Log.i(
                TAG,
                "onItemRangeChanged: positionStart : $positionStart , itemCount : $itemCount"
            )
        }

        override fun onItemRangeChanged(positionStart: Int, itemCount: Int, payload: Any?) {
            super.onItemRangeChanged(positionStart, itemCount, payload)
            Log.i(
                TAG,
                "onItemRangeChanged: positionStart : $positionStart itemCount : $itemCount payload : $payload"
            )
        }

        override fun onItemRangeInserted(positionStart: Int, itemCount: Int) {
            super.onItemRangeInserted(positionStart, itemCount)
            Log.i(TAG, "onItemRangeInserted: positionStart : $positionStart itemCount : $itemCount")
        }

        override fun onItemRangeRemoved(positionStart: Int, itemCount: Int) {
            super.onItemRangeRemoved(positionStart, itemCount)
            Log.i(TAG, "onItemRangeRemoved: positionStart : $positionStart itemCount : $itemCount")
        }

        override fun onItemRangeMoved(fromPosition: Int, toPosition: Int, itemCount: Int) {
            super.onItemRangeMoved(fromPosition, toPosition, itemCount)
            Log.i(
                TAG,
                "onItemRangeMoved: fromPosition : $fromPosition, toPosition : $toPosition, itemCount : $itemCount"
            )
        }
    }

    private fun setupEdgeToEdgeDisplay() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
    }

    private fun getScrollState(state: Int) = when (state) {
        ViewPager2.SCROLL_STATE_IDLE -> "SCROLL_STATE_IDLE"
        SCROLL_STATE_DRAGGING -> "SCROLL_STATE_DRAGGING"
        SCROLL_STATE_SETTLING -> "SCROLL_STATE_SETTLING"
        else -> ""
    }


}