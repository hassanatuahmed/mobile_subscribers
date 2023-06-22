@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mobilesub.ui.theme.view

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.runtime.setValue
import androidx.compose.ui.geometry.Size


import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue

import androidx.compose.ui.unit.toSize

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.input.KeyboardType

import com.example.mobilesub.R
import com.example.mobilesub.data.models.Action
import com.example.mobilesub.data.models.Subscriber
import java.util.Calendar


@Composable
fun DetailAppBar(selectedUser:Subscriber?,navigateToListScreen:(Action) -> Unit){
    if(selectedUser == null){
        DetailAppBarNew(
            NavigateToListScreen =navigateToListScreen )

    }else{
        DetailAppBarEx(
            NavigateToListScreen = navigateToListScreen,
            selectedUser = selectedUser)
    }

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SubscribersDetail(viewModel: SharedViewModel,NavigateToListScreen:(Action) -> Unit) {



        Column(Modifier.padding(top = 60.dp)) {
            TextHeader(text = "Subscriber Name")
            MyTextField(value = viewModel.nameInput, onValueChange = { viewModel.nameInput = it })
            TextHeader(text = "Email")
            MyTextField(value = viewModel.emailInput, onValueChange = { viewModel.emailInput = it })
            TextHeader(text = "Phone Number")
            MyTextField(value = viewModel.phoneInput, onValueChange = { viewModel.phoneInput = it })
            TextHeader(text = "Date of Birth",)
            DatePickerField(viewModel)
            TextHeader(text = "Location")
            MyTextField(
                value = viewModel.locationInput,
                onValueChange = { viewModel.locationInput = it })
            TextHeader(text = "Status")
            DropdownField(viewModel)
            Button(
                onClick = { viewModel.addUser() },
                shape = RoundedCornerShape(10.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.button_color) // Set the desired background color here
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp)

                    .height(60.dp)
            ) {
                Text(text = "Save")

            }
        }

    }


@Composable
fun TextHeader(text: String) {
    Text(
        text = text,
        style = TextStyle(color = colorResource(id = R.color.text_header_color)),

        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextField(modifier: Modifier = Modifier, value: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(
        value = value,

        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 10.dp)
            .height(50.dp),
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownField(sharedViewModel: SharedViewModel) {
    val items = listOf("Prepaid", "Postpaid")


    var icon = if (sharedViewModel.mExpended) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }


    Column {
        OutlinedTextField(value = sharedViewModel.statusInput,
            onValueChange = { sharedViewModel.statusInput = it },
            readOnly = true,
            shape = RoundedCornerShape(10.dp),
            trailingIcon = {
                Icon(icon, contentDescription = "", Modifier.clickable { sharedViewModel.mExpended = !sharedViewModel.mExpended })

            },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top=10.dp)
                .clickable { sharedViewModel.mExpended = true }
                .onGloballyPositioned { coordinates -> sharedViewModel.mTextFieldSize = coordinates.size.toSize() }

        )
        DropdownMenu(
            expanded = sharedViewModel.mExpended,
            onDismissRequest = { sharedViewModel.mExpended = false },
            modifier = Modifier

                .width(with(LocalDensity.current) { sharedViewModel.mTextFieldSize.width.toDp() })
        ) {
            items.forEach { item ->
                DropdownMenuItem(onClick = {
                    sharedViewModel.mExpended = false
                    sharedViewModel.statusInput = item
                }, colors = MenuDefaults.itemColors(
                    colorResource(id = R.color.text_header_color)
                ),
                    text = { Text(text = item, color = Color.Black) })

            }
        }
    }

}

@Composable
fun DatePickerField(sharedViewModel: SharedViewModel) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()

// Fetching current year, month and day
    val year = calendar[Calendar.YEAR]
    val month = calendar[Calendar.MONTH]
    val dayOfMonth = calendar[Calendar.DAY_OF_MONTH]

    val datePicker = DatePickerDialog(
        context,
        { _: DatePicker, selectedYear: Int, selectedMonth: Int, selectedDayOfMonth: Int ->
            sharedViewModel.dobInput = "$selectedDayOfMonth/${selectedMonth + 1}/$selectedYear"
        }, year, month, dayOfMonth
    )

    Column {
        OutlinedTextField(
            value =  sharedViewModel.dobInput,
            shape = RoundedCornerShape(10.dp),

            onValueChange = {  sharedViewModel.dobInput = it },
            readOnly = true,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(top = 10.dp)
                .onGloballyPositioned { coordinates -> sharedViewModel.mTextFieldSize = coordinates.size.toSize() }
                .clickable {  sharedViewModel.isDatePickerVisible = true },

            trailingIcon = {
                IconButton(onClick = { datePicker.show() }) {
                    Icon(Icons.Filled.DateRange, contentDescription = "")

                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.NumberPassword)
        )

    }

}






