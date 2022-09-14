package com.nikhil.here.viewpager_test.ui.state

import androidx.compose.runtime.mutableStateListOf
import com.nikhil.here.viewpager_test.domain.entity.LiveFeedItem

data class MainActivityState(
    val liveFeed: List<LiveFeedItem> = mutableStateListOf()
)