package uz.nabijonov.otabek.todoapp_bek.ui.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import uz.nabijonov.otabek.todoapp_bek.R
import uz.nabijonov.otabek.todoapp_bek.ui.theme.TodoAppTheme

@Composable
fun MyTextField(
    modifier: Modifier = Modifier,
    placeholder: String,
    value: String,
    keyboardOption: KeyboardOptions,
    onValueChange: (String) -> Unit
) {

    BasicTextField(
        keyboardOptions = keyboardOption,
        value = value,
        onValueChange = { newText -> onValueChange.invoke(newText) },
        textStyle = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = Color.DarkGray
        ),
        decorationBox = { innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(size = 8.dp))
                    .border(
                        width = 2.dp,
                        color = colorResource(id = R.color.blue),
                        shape = RoundedCornerShape(size = 8.dp)
                    )
                    .padding(horizontal = 16.dp, vertical = 12.dp), // inner padding
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = placeholder,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal,
                        color = Color.LightGray
                    )
                }
                innerTextField()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun MyTextFieldPreview() {
    var value by remember {
        mutableStateOf("")
    }
    TodoAppTheme {
        MyTextField(
            modifier = Modifier.padding(8.dp), placeholder = "", value = value,
            keyboardOption = KeyboardOptions(keyboardType = KeyboardType.Text)
        ) {
            value = it
        }
    }
}