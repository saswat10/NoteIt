package com.ibml.noteit.feature_note.presentation.notes.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.ColorUtils
import com.ibml.noteit.feature_note.domain.model.Note

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: Note,
    cornerRadius: Dp = 10.dp,
    cutCornerRadius: Dp = 30.dp,
    onDeleteClick: () -> Unit
) {
    Box(modifier = modifier) {
        /*
        *
        * There is a difference between fillMaxSize and matchParentSize
        *
        * The thing with canvas is that they need a fixed size
        * Hence we need relative size units
        *
        * The thing with matchParentSize is that it will give the canvas
        * its size after the parent has measured its constraints
        *
        * If we use fillMaxSize() this will affect the parent size
        * because if the parent does not have a modifier with a fixed size
        * then the canvas would stretch the box
        */
        Canvas(modifier = Modifier.matchParentSize()) {
            val clipPath = Path().apply() {
                lineTo(size.width - cutCornerRadius.toPx(), 0f)
                lineTo(size.width, cutCornerRadius.toPx())
                lineTo(size.width, size.height)
                lineTo(0f, size.height)
                close()
            }

            clipPath(clipPath) {
                drawRoundRect(
                    color = Color(note.color),
                    size = size,
                    cornerRadius = CornerRadius(cornerRadius.toPx())

                )
                drawRoundRect(
                    color = Color(
                        ColorUtils.blendARGB(note.color, 0x00000, 0.2f)
                    ),
                    topLeft = Offset(size.width - cutCornerRadius.toPx(), -100f),
                    size = Size(cutCornerRadius.toPx() + 100f, cutCornerRadius.toPx() + 100f),
                    cornerRadius = CornerRadius(cornerRadius.toPx())
                )

            }
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(end = 32.dp)
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.headlineMedium,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = note.content,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )

            IconButton(
                modifier = Modifier.align(Alignment.End),
                onClick = onDeleteClick,
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete Note"
                )
            }
        }
    }
}