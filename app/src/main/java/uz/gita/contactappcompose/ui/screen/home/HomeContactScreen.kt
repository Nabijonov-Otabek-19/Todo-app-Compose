package uz.gita.contactappcompose.ui.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.contactappcompose.data.common.ContactData
import uz.gita.contactappcompose.ui.component.ContactItem
import uz.gita.contactappcompose.ui.screen.addcontact.AddContactScreen
import uz.gita.contactappcompose.ui.theme.ContactAppComposeTheme
import uz.gita.contactappcompose.ui.viewmodel.AddContactViewModel
import uz.gita.contactappcompose.ui.viewmodel.HomeViewModel
import uz.gita.contactappcompose.ui.viewmodel.impl.AddContactViewModelImpl
import uz.gita.contactappcompose.ui.viewmodel.impl.HomeViewModelImpl

class HomeContactScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = getViewModel<HomeViewModelImpl>()
        HomeContactScreenContent(viewModel)
    }
}

@Composable
fun HomeContactScreenContent(
    viewModel: HomeViewModel,
    modifier: Modifier = Modifier.fillMaxSize()
) {
    val contacts: State<List<ContactData>?> = viewModel.contactsLiveData.observeAsState()
    val contactList: List<ContactData>? = contacts.value

    Box() {
        val navigator = LocalNavigator.currentOrThrow

        LazyColumn(content = {
            if (contactList != null) {
                itemsIndexed(contactList) { index, contactItem ->
                    ContactItem(
                        fname = contactItem.firstName,
                        lname = contactItem.lastName,
                        phone = contactItem.phone
                    )
                    if (index != contactList.size - 1)
                        Spacer(modifier = Modifier.size(8.dp))
                }
            }
        })

        FloatingActionButton(
            shape = RoundedCornerShape(16.dp),
            containerColor = Color.Blue,
            onClick = { navigator.push(AddContactScreen()) }) {
            Icon(Icons.Default.Add, contentDescription = "Add", tint = Color.White)
        }
    }
}


@Preview
@Composable
fun AddContactScreenPreview() {
    ContactAppComposeTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
//            AddContactScreenContent()
        }
    }
}