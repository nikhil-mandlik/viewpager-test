package com.nikhil.here.viewpager_test.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.nikhil.here.viewpager_test.domain.entity.LiveFeedItem
import com.nikhil.here.viewpager_test.ui.sideeffects.MainActivitySideEffect
import com.nikhil.here.viewpager_test.ui.state.MainActivityState
import com.nikhil.here.viewpager_test.ui.utility.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() :
    ContainerHost<MainActivityState, MainActivitySideEffect>, ViewModel() {


    override val container =
        container<MainActivityState, MainActivitySideEffect>(MainActivityState(Constants.liveFeed))

    private val mDownStreamEventChannel = Channel<Int>(Channel.UNLIMITED)
    val downStreamEvent = mDownStreamEventChannel.receiveAsFlow()

    init {
        intent {
            startFlow()
        }
    }

    private suspend fun startFlow() {
        var i = 0
        while (true){
            delay(1000)
            mDownStreamEventChannel.trySend(i++)
        }
    }

    fun addThreeLiveFeedItems() = intent {
        val items = mutableStateListOf<LiveFeedItem>()
        repeat(3) {
            items.add(LiveFeedItem.UpcomingLiveEventsLiveFeedItem(pageId = "$it - ${System.currentTimeMillis()}"))
        }
        reduce {
            val updatedList = state.liveFeed.toMutableList()
            updatedList.addAll(items)
            state.copy(
                liveFeed = updatedList
            )
        }
    }

    fun removeCurrent() = intent {
        reduce {
            val updatedList = state.liveFeed.toMutableList()
            updatedList.removeAt(0)
            state.copy(
                liveFeed = updatedList
            )
        }
    }
}