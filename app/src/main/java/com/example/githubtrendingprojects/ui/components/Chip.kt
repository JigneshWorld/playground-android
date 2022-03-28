package com.example.githubtrendingprojects.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.githubtrendingprojects.ui.theme.Teal200

@Composable
fun Chip(label: String, modifier: Modifier = Modifier) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
        border = BorderStroke(
            width = 1.dp,
            color = Teal200
        ),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.caption.copy(color = Teal200),
            modifier = Modifier.padding(4.dp),
        )
    }
}