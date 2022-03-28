package com.example.githubtrendingprojects.ui.screens.project_details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.AccountTree
import androidx.compose.material.icons.outlined.Link
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.githubtrendingprojects.R
import com.example.githubtrendingprojects.ui.components.Center
import com.example.githubtrendingprojects.ui.theme.AppContent
import com.example.githubtrendingprojects.utils.getFormattedNumber
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun ProjectDetailsScreen(viewModel: ProjectDetailsViewModel, onBackClick: () -> Unit) {
    Page(
        onBackClick = onBackClick,
        content = {
            when (val uiState = viewModel.uiState) {
                is ProjectDetailsUiState.Empty -> Center {
                    Text(stringResource(id = R.string.message_empty_project_details))
                }
                is ProjectDetailsUiState.Loading -> Center {
                    CircularProgressIndicator()
                }
                is ProjectDetailsUiState.Success -> ProjectDetails(uiState.data)
                is ProjectDetailsUiState.Error -> Center {
                    Text(text = uiState.message)
                }
            }
        },
    )
}

@Composable
private fun Page(content: @Composable () -> Unit, onBackClick: () -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(stringResource(id = R.string.title_project_details))
                },
                navigationIcon = {
                    IconButton(onClick = onBackClick) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                    }
                }
            )
        }
    ) {
        content()
    }
}


@Composable
private fun ProjectDetails(model: ProjectDetailsUiModel) {
    val project = model.projectDetails
    val spacing = 12.dp
    val styleSubTitle = MaterialTheme.typography.subtitle1.copy(fontWeight = FontWeight.SemiBold)
    Column(modifier = Modifier.padding(spacing)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = rememberAsyncImagePainter(project.owner.avatarUrl),
                contentDescription = null,
                modifier = Modifier
                    .height(32.dp)
                    .width(32.dp)
            )
            Spacer(modifier = Modifier.width(spacing))
            Text(text = project.owner.login)
        }
        Spacer(modifier = Modifier.height(spacing))
        Text(text = project.name, style = MaterialTheme.typography.h6)
        Spacer(modifier = Modifier.height(spacing))
        project.description?.let { description ->
            Text(text = description)
            Spacer(modifier = Modifier.height(spacing))
        }
        project.homepageUrl?.let { link ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.Link,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp),
                )
                Text(text = link)
            }
            Spacer(modifier = Modifier.height(spacing))
        }
        Row {
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.Star,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp),
                )

                Text(
                    text = stringResource(
                        id = R.string.number_stars,
                        getFormattedNumber(project.stargazerCount)
                    ),
                )
            }
            Row(modifier = Modifier.weight(1f), verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    Icons.Outlined.AccountTree,
                    contentDescription = null,
                    modifier = Modifier.padding(end = 4.dp),
                )
                Text(
                    text = stringResource(
                        id = R.string.number_forks,
                        getFormattedNumber(project.forkCount)
                    ),
                )
            }
        }
        Spacer(modifier = Modifier.height(spacing))
        Text(text = stringResource(id = R.string.subtitle_states), style = styleSubTitle)
        Spacer(modifier = Modifier.height(spacing / 2))
        Row {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = getFormattedNumber(project.issuesCount))
                Text(text = stringResource(id = R.string.subtitle_issues), style = styleSubTitle)
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = getFormattedNumber(project.pullRequestsCount))
                Text(
                    text = stringResource(id = R.string.subtitle_pull_requests),
                    style = styleSubTitle
                )
            }

        }
        Spacer(modifier = Modifier.height(spacing))
        Row {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = getFormattedNumber(project.discussionsCount))
                Text(
                    text = stringResource(id = R.string.subtitle_discussions),
                    style = styleSubTitle
                )
            }
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(text = getFormattedNumber(project.releasesCount))
                Text(text = stringResource(id = R.string.subtitle_releases), style = styleSubTitle)
            }
        }
        Spacer(modifier = Modifier.height(spacing))
        if (project.languages.isNotEmpty()) {
            Text(text = stringResource(id = R.string.subtitle_languages), style = styleSubTitle)
            Spacer(modifier = Modifier.height(spacing / 2))
            FlowRow(
                mainAxisSize = SizeMode.Expand,
                crossAxisSpacing = 12.dp,
                mainAxisSpacing = 8.dp,
            ) {
                project.languages.forEach { language ->
                    Text(
                        text = language.name,
                        modifier = Modifier
                            .background(
                                color = Color(android.graphics.Color.parseColor(language.color)),
                                shape = RoundedCornerShape(4.dp)
                            )
                            .padding(8.dp),
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1
                    )
                }
            }
            Spacer(modifier = Modifier.height(spacing))
        }
        if (project.mentionableUsers.isNotEmpty()) {
            val moreCount = project.mentionableUsersCount - project.mentionableUsers.size
            Text(text = stringResource(id = R.string.subtitle_mentions), style = styleSubTitle)
            Spacer(modifier = Modifier.height(spacing / 2))
            Column {
                LazyRow(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    items(project.mentionableUsers) { user ->
                        Image(
                            painter = rememberAsyncImagePainter(user.avatarUrl),
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .border(1.dp, Color.Gray, CircleShape)
                        )
                    }
                    if (moreCount > 0) {
                        item {
                            Text(
                                text = stringResource(
                                    id = R.string.message_others,
                                    moreCount,
                                ),
                            )
                        }
                    }

                }
            }
            Spacer(modifier = Modifier.height(spacing))
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    AppContent {
        Page(
            onBackClick = {},
            content = {
                ProjectDetails(mockProjectDetailsUiModel)
            },
        )
    }
}