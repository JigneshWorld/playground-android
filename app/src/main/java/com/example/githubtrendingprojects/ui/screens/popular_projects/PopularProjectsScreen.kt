package com.example.githubtrendingprojects.ui.screens.popular_projects

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.githubtrendingprojects.R
import com.example.githubtrendingprojects.model.Project
import com.example.githubtrendingprojects.ui.components.Center
import com.example.githubtrendingprojects.ui.components.Chip
import com.example.githubtrendingprojects.ui.theme.AppTheme
import com.example.githubtrendingprojects.utils.getFormattedNumber
import org.koin.androidx.compose.getViewModel


typealias ProjectClickListener = (name: String, owner: String) -> Unit

@Composable
fun PopularProjectsScreen(
    viewModel: PopularProjectsViewModel = getViewModel(),
    onProjectClick: ProjectClickListener
) {
    Page {
        when (val uiState = viewModel.uiState) {
            is PopularProjectsUiState.Empty ->
                Center {
                    Text(text = stringResource(id = R.string.message_empty_projects))
                }
            is PopularProjectsUiState.Loading ->
                Center {
                    CircularProgressIndicator()
                }
            is PopularProjectsUiState.Success -> ProjectsList(uiState.data, onProjectClick)
            is PopularProjectsUiState.Error ->
                Center {
                    Text(uiState.message)
                }
        }
    }
}

@Composable
private fun Page(content: @Composable () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.title_popular_projects))
                }
            )
        }
    ) {
        content()
    }
}

@Composable
private fun ProjectsList(model: PopularProjectsUiModel, onProjectClick: ProjectClickListener) {
    LazyColumn {
        items(items = model.projects) { project ->
            ProjectItem(project, onProjectClick)
        }
    }
}

@Composable
private fun ProjectItem(project: Project, onProjectClick: ProjectClickListener) {
    val spacing = 12.dp
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(spacing)
            .clickable(onClick = {
                onProjectClick(project.name, project.owner.login)
            }),
    ) {
        Image(
            painter = rememberAsyncImagePainter(project.owner.avatarUrl),
            contentDescription = null,
            modifier = Modifier
                .width(52.dp)
                .height(52.dp),
        )
        Column(
            modifier = Modifier
                .padding(start = spacing)
                .weight(1f)
        ) {
            Text(
                text = project.nameWithOwner,
                style = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.Bold),
            )
            project.description?.let { description ->
                Text(text = description, style = MaterialTheme.typography.caption)
            }
            project.primaryLanguage?.let { language ->
                Chip(language, modifier = Modifier.padding(top = 4.dp))
            }
        }
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                Icons.Rounded.Star,
                contentDescription = null,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            )
            Text(
                text = getFormattedNumber(project.stargazerCount),
                style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
            )
        }

    }
    Divider()
}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AppTheme {
        Page {
            ProjectsList(mockPopularProjectsUiModel, onProjectClick = { _, _ -> })
        }
    }
}

