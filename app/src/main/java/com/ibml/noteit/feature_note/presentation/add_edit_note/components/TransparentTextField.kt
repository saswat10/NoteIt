package com.ibml.noteit.feature_note.presentation.add_edit_note.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle

@Composable
fun TransparentTextField(
    modifier:Modifier = Modifier,
    text: String,
    textStyle: TextStyle = TextStyle(),
    hint: String,
    isHintVisible: Boolean= true,
    onValChange: (String) -> Unit,
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
    Box(modifier= modifier ){
        BasicTextField(
            value = text,
            onValueChange = onValChange,
            singleLine= singleLine,
            textStyle = textStyle,
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent{onFocusChange(it)}
        )
        if(isHintVisible){
            Text(text = hint, style = textStyle, color = Color.DarkGray)
        }
    }
}