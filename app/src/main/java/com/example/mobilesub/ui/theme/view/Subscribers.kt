package com.example.mobilesub.ui.theme.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobilesub.R
import com.example.mobilesub.data.models.Subscriber


enum class MyScreen(@StringRes val title: Int) {
    Start(title = R.string.subscribers),
    SubscriberDetail(title = R.string.sub_detail)

}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainList(
    modifier: Modifier = Modifier,
    navigateToDetailPage: (userId: Int) -> Unit, sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        Log.d("listscreen", "launch effect triggered")
        sharedViewModel.getAllUserFlow()
    }
//    val allUserState by sharedViewModel.data.collectAsState()
    val allSubscriber by sharedViewModel.allUsers.observeAsState(emptyList<Subscriber>())
//    val testLiveDate by sharedViewModel.dataLive.collectAsState()

    Scaffold(floatingActionButton =
    { MyFloatingActionButton(navigateToDetailPage = navigateToDetailPage) },
        topBar = { MyAppBar(stringResource(id = R.string.subscribers)) }) {
        ListContent(subscriberList = allSubscriber,navigateToDetailPage)

    }


}

@Composable
fun ListContent(subscriberList: List<Subscriber>, navigateToDetailPage: (userId: Int) -> Unit) {
    LazyColumn(modifier = Modifier.padding(top = 60.dp)) {
        items(
            items = subscriberList,
            key = { user ->
                user.id }
        ) {myList ->
            ListItem(subscriber = myList, navigateToDetailPage =navigateToDetailPage )


        }


    }
}


