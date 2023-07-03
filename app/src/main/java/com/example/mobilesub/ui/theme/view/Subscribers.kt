package com.example.mobilesub.ui.theme.view

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.mobilesub.R
import com.example.mobilesub.data.models.RequestState
import com.example.mobilesub.data.models.Subscriber
import com.example.mobilesub.ui.theme.components.ListItem
import com.example.mobilesub.ui.theme.components.MyAppBar
import com.example.mobilesub.ui.theme.components.MyFloatingActionButton


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainList(
    modifier: Modifier = Modifier,
    navigateToDetailPage: (userId: Int) -> Unit, sharedViewModel: SharedViewModel
) {

    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllUserFlow()
    }

    val action by sharedViewModel.action
    val allUserState by sharedViewModel.data.collectAsState()
    val allSubscriber by sharedViewModel.allUsers.observeAsState(RequestState.Loading)
    sharedViewModel.handleDatabaseActions(action)

    Scaffold(floatingActionButton = {
        MyFloatingActionButton(navigateToDetailPage = navigateToDetailPage) },

        topBar = { MyAppBar(stringResource(id = R.string.subscribers)) }) {
        ListContent(subscriberList = allUserState,navigateToDetailPage)

    }


}

@Composable
fun ListContent(subscriberList: RequestState<List<Subscriber>>, navigateToDetailPage: (userId: Int) -> Unit) {
    if(subscriberList is RequestState.Success){
        if(subscriberList.data.isEmpty()){
            EmptyContent()
        }else{
            DisplaySubscriber(subscriberList = subscriberList.data, navigateToDetailPage =navigateToDetailPage )
        }
    }

}

@Composable
fun DisplaySubscriber(subscriberList: List<Subscriber>, navigateToDetailPage: (userId: Int) -> Unit){
    LazyColumn(modifier = Modifier.padding(top = 60.dp)) {
        items(

            items = subscriberList,
            key = { user ->
                user.id }
        ) {myList ->
            ListItem(subscriber = myList,
                navigateToDetailPage =navigateToDetailPage,
                modifier = Modifier )


        }


    }
}


