package uz.gita.contactappcompose.ui.screen.addcontact

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.contactappcompose.data.common.ContactData
import uz.gita.contactappcompose.ui.component.MyTextField
import uz.gita.contactappcompose.ui.viewmodel.AddEditContract
import uz.gita.contactappcompose.ui.viewmodel.impl.AddContactViewModelImpl

class AddScreen(private val updateData: ContactData?) : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: AddEditContract.ViewModel = getViewModel<AddContactViewModelImpl>()
        AddContactScreenContent( viewModel::onEventDispatcher, updateData)
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddContactScreenContent(
    onEventDispatcher: (AddEditContract.Intent) -> Unit,
    updateData: ContactData?
) {
    var fname by remember { mutableStateOf(updateData?.firstName ?: "") }
    var lname by remember { mutableStateOf(updateData?.lastName ?: "") }
    var phone by remember { mutableStateOf(updateData?.phone ?: "") }

    val isUpdate = updateData != null

    val navigator = LocalNavigator.currentOrThrow

    Scaffold(
        topBar = { TopAppBar(title = { Text("Add Contact") }) },
    )
    { contentPadding ->
        Column(
            modifier = Modifier.padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "First name")
            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "First name", value = fname,
                onValueChange = { fname = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Last name")
            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "Last name", value = lname,
                onValueChange = { lname = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
            )

            Text(modifier = Modifier.padding(start = 8.dp, top = 4.dp), text = "Phone name")
            MyTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                placeholder = "Number", value = phone,
                onValueChange = { phone = it },
                keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Phone)
            )

            ElevatedButton(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 8.dp), onClick = {
                if (!isUpdate && fname.isNotEmpty() && lname.isNotEmpty() && phone.isNotEmpty()) {
                    onEventDispatcher(AddEditContract.Intent.AddContact(ContactData(firstName = fname, lastName = lname,phone= phone)))
                    navigator.pop()
                } else if (isUpdate && fname.isNotEmpty() && lname.isNotEmpty() && phone.isNotEmpty()) {
                    onEventDispatcher(AddEditContract.Intent.UpdateContact(ContactData(updateData!!.id, fname, lname, phone)))
                    navigator.pop()
                }
            }) {
                Text(text = "Add Contact")
            }
        }
    }
}