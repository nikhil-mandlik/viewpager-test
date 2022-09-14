package com.nikhil.here.viewpager_test.domain.entity

sealed class LiveFeedItem(val feedItemId: String) {
    data class InteractiveLiveFeedItem(val livestreamId: String) :
        LiveFeedItem(feedItemId = livestreamId)

    data class ObsLiveFeedItem(val obsLiveStreamId: String) :
        LiveFeedItem(feedItemId = obsLiveStreamId)

    data class UpcomingLiveEventsLiveFeedItem(val pageId: String) :
        LiveFeedItem(feedItemId = pageId)
}
