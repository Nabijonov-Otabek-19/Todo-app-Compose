package uz.gita.contactappcompose.ui.screen.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.androidx.AndroidScreen
import cafe.adriel.voyager.hilt.getViewModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import uz.gita.contactappcompose.ui.component.ContactItem
import uz.gita.contactappcompose.ui.screen.addcontact.AddContactScreen
import uz.gita.contactappcompose.ui.theme.ContactAppComposeTheme
import uz.gita.contactappcompose.ui.viewmodel.HomeViewModel
import uz.gita.contactappcompose.ui.viewmodel.impl.HomeViewModelImpl
import uz.gita.contactappcompose.utils.logger

class HomeContactScreen : AndroidScreen() {
    @Composable
    override fun Content() {
        val viewModel: HomeViewModel = getViewModel<HomeViewModelImpl>()
        ContactAppComposeTheme {
            Surface(modifier = Modifier.fillMaxSize()) {
                HomeContactScreenContent(viewModel)
            }
        }
    }
}

@Composable
fun HomeContactScreenContent(
    viewModel: HomeViewModel
) {
    val contacts = viewModel.contactsLiveData.observeAsState(listOf())

    logger("Contact list = ${contacts.value.size}")

    Box(modifier = Modifier.fillMaxSize()) {
        val navigator = LocalNavigator.currentOrThrow

        LazyColumn(
            modifier = Modifier.padding(horizontal = 8.dp),
            content = {
                items(contacts.value) {

                    Spacer(modifier = Modifier.size(8.dp))

                    ContactItem(
                        fname = it.firstName,
                        lname = it.lastName,
                        phone = it.phone
                    )
                }
            })

        FloatingActionButton(
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.BottomEnd),
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