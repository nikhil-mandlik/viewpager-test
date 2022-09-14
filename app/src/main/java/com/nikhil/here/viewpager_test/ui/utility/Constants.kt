package com.nikhil.here.viewpager_test.ui.utility

import com.nikhil.here.viewpager_test.domain.entity.LiveFeedItem

object Constants {
    const val INTERACTIVE_LIVESTREAM_ID = "liveStreamId"
    const val OBS_LIVESTREAM_ID = "obsLiveStreamId"
    const val UPCOMING_LIVE_EVENTS_PAGE_ID = "upcomingLiveEventsPageId"

    val liveFeed = listOf<LiveFeedItem>(
        LiveFeedItem.InteractiveLiveFeedItem(livestreamId = "1234"),
        LiveFeedItem.ObsLiveFeedItem(obsLiveStreamId = "1235"),
        LiveFeedItem.UpcomingLiveEventsLiveFeedItem(pageId = "1236")
    )
}