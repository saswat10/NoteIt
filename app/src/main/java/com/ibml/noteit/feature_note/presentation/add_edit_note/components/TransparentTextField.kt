package com.ibml.noteit.feature_note.presentation.add_edit_note.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState

@Composable
fun TransparentTextField(
    modifier:Modifier = Modifier,
    text: String,
    hint: String,
    isHintVisible: Boolean= true,
    onValChange: (String) -> Unit,
    singleLine: Boolean = false,
    onFocusChange: (FocusState) -> Unit
) {
}