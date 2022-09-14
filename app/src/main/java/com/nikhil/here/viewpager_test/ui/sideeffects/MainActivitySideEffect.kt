package com.nikhil.here.viewpager_test.ui.sideeffects

import com.nikhil.here.viewpager_test.domain.entity.LiveFeedItem

sealed class MainActivitySideEffect {
    data class UpdateLiveFeed(val liveFeed: List<LiveFeedItem>) : MainActivitySideEffect()
}