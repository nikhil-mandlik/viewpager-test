package com.nikhil.here.viewpager_test.ui.adapter

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nikhil.here.viewpager_test.domain.entity.LiveFeedItem
import com.nikhil.here.viewpager_test.ui.fragment.InteractiveLiveStreamFragment
import com.nikhil.here.viewpager_test.ui.fragment.ObsLiveStreamFragment
import com.nikhil.here.viewpager_test.ui.fragment.UpcomingLiveEventsFragment
import com.nikhil.here.viewpager_test.ui.utility.Constants

class LiveFeedAdapter(
    private val mFragmentManager: FragmentManager,
    private val mLifecycleOwner: LifecycleOwner
) : FragmentStateAdapter(mFragmentManager, mLifecycleOwner.lifecycle) {

    private val mLiveFeedDiffer = AsyncListDiffer(this, LiveFeedDiffer())

    override fun getItemCount() = mLiveFeedDiffer.currentList.size

    override fun createFragment(position: Int): Fragment {
        return when (val liveFeedItem = mLiveFeedDiffer.currentList[position]) {
            is LiveFeedItem.InteractiveLiveFeedItem -> {
                InteractiveLiveStreamFragment.getFragment(
                    liveStreamId = liveFeedItem.livestreamId
                ).apply {
                    bundleOf(Constants.INTERACTIVE_LIVESTREAM_ID to liveFeedItem.livestreamId)
                }
            }
            is LiveFeedItem.ObsLiveFeedItem -> {
                ObsLiveStreamFragment.getFragment(
                    obsLiveStreamId = liveFeedItem.obsLiveStreamId
                )
            }
            is LiveFeedItem.UpcomingLiveEventsLiveFeedItem -> {
                UpcomingLiveEventsFragment.getFragment(
                    pageId = liveFeedItem.pageId
                )
            }
        }
    }

    fun updateLiveFeed(liveFeed: List<LiveFeedItem>) {
        mLiveFeedDiffer.submitList(liveFeed)
    }

    inner class LiveFeedDiffer : DiffUtil.ItemCallback<LiveFeedItem>() {
        override fun areItemsTheSame(oldItem: LiveFeedItem, newItem: LiveFeedItem) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: LiveFeedItem, newItem: LiveFeedItem) =
            oldItem == newItem
    }

    override fun getItemId(position: Int): Long {
        return mLiveFeedDiffer.currentList[position].feedItemId.hashCode().toLong()
    }

    override fun containsItem(itemId: Long): Boolean {
        return mLiveFeedDiffer.currentList.any { it.feedItemId.hashCode().toLong() == itemId }
    }
}