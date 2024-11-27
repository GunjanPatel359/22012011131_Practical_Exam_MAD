package com.example.myappjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventDetailsScreen()
        }
    }
}

// Define data class for Event
data class Event(
    val time: String,
    val title: String
)

// Define data class for Review
data class Review(
    val profileImage: Int,
    val userName: String,
    val comment: String
)

// Sample data for events and reviews
val eventList = listOf(
    Event("10:00 AM", "Opening Ceremony"),
    Event("12:00 PM", "Tech Talk 1"),
    Event("02:00 PM", "Lunch Break"),
    Event("03:00 PM", "Panel Discussion")
)

val userReviews = listOf(
    Review(R.drawable.user, "Alice", "Great event, very informative!"),
    Review(R.drawable.user, "Bob", "Loved the networking opportunities."),
    Review(R.drawable.user, "Charlie", "Well-organized and engaging.")
)

// Composable function for EventDetailsScreen
@Composable
fun EventDetailsScreen() {
    val isDarkTheme = isSystemInDarkTheme()

    MaterialTheme(
        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Row {
                Text(
                    "EventEase",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(16.dp)
                        .align(Alignment.Top)
                        .background(color = MaterialTheme.colorScheme.background),
                )
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 60.dp)
                    .padding(bottom = 72.dp)
            ) {
                item {
                    Image(
                        painter = painterResource(id = R.drawable.event),
                        contentDescription = "Event Image",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(16.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Text(
                        "Tech Conference 2020",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.headlineMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "Mehsana, Gujarat | 2.5 km away",
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                        "This is a detailed description of the event...",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        "Event Schedule",
                        fontWeight = FontWeight.ExtraBold,
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(eventList.size) { index ->
                            EventScheduleCard(eventList[index])
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier.height(16.dp))

                    // User Reviews
                    Text(
                        "User Reviews",
                        style = MaterialTheme.typography.headlineSmall,
                        modifier = Modifier.padding(horizontal = 16.dp)
                    )
                }

                items(userReviews.size) { index ->
                    UserReviewCard(userReviews[index])
                }
            }

            // Fixed Buttons
            Row(
                modifier = Modifier
                    .fillMaxWidth()
//                    .align(Alignment.BottomCenter)
                    .align(Alignment.BottomEnd)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /* Handle Buy Tickets */ }) {
                    Text("Buy Tickets")
                }
                OutlinedButton(onClick = { /* Handle Add to Calendar */ }) {
                    Text("Add to Calendar")
                }
            }
        }
    }
}



// EventScheduleCard composable
@Composable
fun EventScheduleCard(event: Event) {
    Card(
        modifier = Modifier
            .width(200.dp)
            .height(180.dp)
            .padding(2.dp),
        elevation = CardDefaults.cardElevation(14.dp)
    ) {
        Column(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                text = event.time,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 18.sp,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = event.title,
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 24.sp,
                    lineHeight = 28.sp
                )
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = "This is a detailed description of the event...",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontSize = 14.sp,
                )
            )
        }
    }
}


// UserReviewCard composable
@Composable
fun UserReviewCard(review: Review) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Image(
            painter = painterResource(id = review.profileImage), // Ensure profile image exists
            contentDescription = "Profile Image",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(review.userName, style = MaterialTheme.typography.bodyMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(review.comment, style = MaterialTheme.typography.bodySmall)
        }
    }
}