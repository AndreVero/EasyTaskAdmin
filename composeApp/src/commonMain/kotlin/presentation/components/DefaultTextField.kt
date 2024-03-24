package presentation.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DefaultTextField(
    hint: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    Text(text = hint, color = Color.Gray)
    Spacer(modifier = Modifier.height(16.dp))
    TextField(
        maxLines = 1,
        minLines = 1,
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier.width(300.dp)
    )
}