package com.healthvista.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.healthvista.app.ui.theme.HealthVistaTheme

private val Teal = Color(0xFF087F80)
private val DarkTeal = Color(0xFF075B60)
private val Mint = Color(0xFFE5F5F1)
private val Page = Color(0xFFF5F8F7)
private val Ink = Color(0xFF193334)
private val Muted = Color(0xFF6B8080)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { HealthVistaTheme { HealthVistaApp() } }
    }
}

private enum class Tab(val title: String, val icon: String) {
    Home("Home", "⌂"), Progress("Progress", "◷"), Coach("AI Coach", "✦"),
    Reminders("Reminders", "◉"), Profile("Profile", "☺")
}

@Composable
private fun HealthVistaApp() {
    var tab by remember { mutableStateOf(Tab.Home) }
    var elder by remember { mutableStateOf(false) }
    var question by remember { mutableStateOf("") }
    var chat by remember { mutableStateOf(listOf("Hello! I’m your HealthVista wellness coach. How can I help today?")) }
    var newReminder by remember { mutableStateOf("") }
    var reminders by remember { mutableStateOf(listOf("Drink a glass of water", "Take a 10-minute walk", "Prepare for bedtime")) }
    val scale = if (elder) 1.18f else 1f

    MaterialTheme(colorScheme = lightColorScheme(primary = Teal, secondary = DarkTeal, background = Page, surface = Color.White, onSurface = Ink)) {
        Scaffold(
            containerColor = Page,
            bottomBar = {
                NavigationBar(containerColor = Color.White) {
                    Tab.values().forEach { item ->
                        NavigationBarItem(
                            selected = tab == item, onClick = { tab = item },
                            icon = { Text(item.icon, fontSize = if (elder) 25.sp else 21.sp) },
                            label = { Text(item.title, fontSize = if (elder) 12.sp else 10.sp) },
                            colors = NavigationBarItemDefaults.colors(selectedIconColor = Teal, selectedTextColor = Teal, indicatorColor = Mint)
                        )
                    }
                }
            }
        ) { padding ->
            Box(Modifier.fillMaxSize().padding(padding).background(Page)) {
                when (tab) {
                    Tab.Home -> HomeScreen(scale, elder) { tab = it }
                    Tab.Progress -> ProgressScreen(scale)
                    Tab.Coach -> CoachScreen(scale, question, { question = it }, chat) {
                        if (question.isNotBlank()) {
                            chat = chat + "You: $question" + "Coach: Thanks for sharing. Try one small healthy step today. For medical concerns, contact a healthcare professional."
                            question = ""
                        }
                    }
                    Tab.Reminders -> ReminderScreen(scale, newReminder, { newReminder = it }, reminders, {
                        if (newReminder.isNotBlank()) { reminders = reminders + newReminder.trim(); newReminder = "" }
                    }, { reminders = reminders - it })
                    Tab.Profile -> ProfileScreen(scale, elder) { elder = it }
                }
            }
        }
    }
}

@Composable
private fun Header(title: String, subtitle: String, scale: Float) {
    Column(Modifier.fillMaxWidth().padding(horizontal = 20.dp, vertical = 18.dp)) {
        Text("HEALTHVISTA", color = Teal, fontSize = (12 * scale).sp, fontWeight = FontWeight.Bold, letterSpacing = 2.sp)
        Text(title, color = Ink, fontSize = (28 * scale).sp, fontWeight = FontWeight.Bold)
        Text(subtitle, color = Muted, fontSize = (14 * scale).sp)
    }
}

@Composable
private fun Panel(modifier: Modifier = Modifier, content: @Composable ColumnScope.() -> Unit) {
    Card(modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Column(Modifier.padding(16.dp), content = content)
    }
}

@Composable
private fun HomeScreen(scale: Float, elder: Boolean, go: (Tab) -> Unit) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 18.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item { Header("Good morning!", "Small steps make a healthier you.", scale) }
        item {
            Card(Modifier.padding(horizontal = 16.dp).fillMaxWidth(), shape = RoundedCornerShape(24.dp), colors = CardDefaults.cardColors(containerColor = DarkTeal)) {
                Column(Modifier.padding(20.dp)) {
                    Text("YOUR WELLNESS TODAY", color = Color(0xFFBDE9DF), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    Text("You’re doing great. Keep going!", color = Color.White, fontSize = (22 * scale).sp, fontWeight = FontWeight.Bold)
                    Text("A little movement, water and rest can make a difference.", color = Color.White, fontSize = (14 * scale).sp)
                    Spacer(Modifier.height(12.dp))
                    Button(onClick = { go(Tab.Progress) }, colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = DarkTeal)) { Text("View my progress") }
                }
            }
        }
        item {
            Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
                Text("Today's overview", fontSize = (20 * scale).sp, fontWeight = FontWeight.Bold)
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Metric("👟", "Steps", "3,250", "of 6,000", Modifier.weight(1f), scale)
                    Metric("💧", "Water", "4 glasses", "of 8 glasses", Modifier.weight(1f), scale)
                }
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    Metric("🌙", "Sleep", "7.5 hrs", "last night", Modifier.weight(1f), scale)
                    Metric("🌿", "Mood", "Good", "keep it up", Modifier.weight(1f), scale)
                }
            }
        }
        item {
            Column(Modifier.padding(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(9.dp)) {
                Text("Quick actions", fontSize = (20 * scale).sp, fontWeight = FontWeight.Bold)
                ActionRow("Talk to AI Coach", "Ask a wellness question", "✦") { go(Tab.Coach) }
                ActionRow("My reminders", "See your daily reminders", "◉") { go(Tab.Reminders) }
                ActionRow("My profile", "Personal and accessibility settings", "☺") { go(Tab.Profile) }
            }
        }
        item { Text(if (elder) "Elder Mode is ON · Larger text and clearer controls" else "Tip: Take a short stretch break every hour.", Modifier.padding(horizontal = 20.dp), color = Teal, fontSize = (13 * scale).sp) }
    }
}

@Composable
private fun Metric(icon: String, title: String, value: String, note: String, modifier: Modifier, scale: Float) {
    Panel(modifier) {
        Text(icon, fontSize = 24.sp)
        Text(title, color = Muted, fontSize = (12 * scale).sp)
        Text(value, color = Ink, fontSize = (18 * scale).sp, fontWeight = FontWeight.Bold)
        Text(note, color = Muted, fontSize = (11 * scale).sp)
    }
}

@Composable
private fun ActionRow(title: String, subtitle: String, icon: String, click: () -> Unit) {
    Card(Modifier.fillMaxWidth().clickable(onClick = click), shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
        Row(Modifier.padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
            Box(Modifier.size(44.dp).background(Mint, CircleShape), contentAlignment = Alignment.Center) { Text(icon, color = Teal, fontSize = 23.sp) }
            Column(Modifier.weight(1f).padding(start = 12.dp)) {
                Text(title, color = Ink, fontWeight = FontWeight.Bold)
                Text(subtitle, color = Muted, fontSize = 12.sp)
            }
            Text("›", color = Teal, fontSize = 28.sp)
        }
    }
}

@Composable
private fun ProgressScreen(scale: Float) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 20.dp), verticalArrangement = Arrangement.spacedBy(13.dp)) {
        item { Header("Your progress", "Track your daily wellness habits.", scale) }
        item { ProgressItem("👟", "Daily steps", "3,250", "6,000 steps goal", .54f, scale) }
        item { ProgressItem("💧", "Water intake", "4 of 8 glasses", "Stay hydrated through the day", .5f, scale) }
        item { ProgressItem("🌙", "Sleep", "7.5 hours", "Goal: 8 hours", .94f, scale) }
        item { ProgressItem("🏃", "Active minutes", "20 minutes", "Goal: 30 minutes", .67f, scale) }
        item {
            Panel(Modifier.padding(horizontal = 16.dp)) {
                Text("Weekly reflection", fontSize = (19 * scale).sp, fontWeight = FontWeight.Bold)
                Text("You are building healthy routines. Celebrate your consistency and choose one small goal for tomorrow.", color = Muted, fontSize = (14 * scale).sp)
            }
        }
        item { Text("Sample values for demonstration. Real device data can be connected in a future version.", Modifier.padding(horizontal = 20.dp), color = Muted, fontSize = 11.sp) }
    }
}

@Composable
private fun ProgressItem(icon: String, title: String, value: String, goal: String, amount: Float, scale: Float) {
    Panel(Modifier.padding(horizontal = 16.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(icon, fontSize = 26.sp)
            Column(Modifier.weight(1f).padding(start = 12.dp)) {
                Text(title, fontSize = (16 * scale).sp, fontWeight = FontWeight.Bold)
                Text(goal, color = Muted, fontSize = (12 * scale).sp)
            }
            Text(value, color = Teal, fontSize = (13 * scale).sp, fontWeight = FontWeight.Bold)
        }
        Spacer(Modifier.height(12.dp))
        LinearProgressIndicator(progress = { amount }, Modifier.fillMaxWidth().height(9.dp), color = Teal, trackColor = Mint)
    }
}

@Composable
private fun CoachScreen(scale: Float, question: String, onQuestion: (String) -> Unit, chat: List<String>, send: () -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Header("AI Wellness Coach", "Friendly guidance for everyday wellbeing.", scale)
        Card(Modifier.weight(1f).fillMaxWidth().padding(horizontal = 16.dp), shape = RoundedCornerShape(20.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
            LazyColumn(Modifier.fillMaxSize().padding(14.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                item {
                    Text("✦ HealthVista Coach", color = Teal, fontWeight = FontWeight.Bold, fontSize = (17 * scale).sp)
                    Text("I can help with general wellness habits, activity ideas and reminders.", color = Muted, fontSize = (13 * scale).sp)
                }
                items(chat) { line ->
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = if (line.startsWith("You:")) Arrangement.End else Arrangement.Start) {
                        Surface(color = if (line.startsWith("You:")) Mint else Page, shape = RoundedCornerShape(16.dp)) {
                            Text(line, Modifier.padding(12.dp).widthIn(max = 280.dp), color = Ink, fontSize = (14 * scale).sp)
                        }
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth().padding(12.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(question, onQuestion, Modifier.weight(1f), placeholder = { Text("Type your question…") }, shape = RoundedCornerShape(18.dp), maxLines = 3)
            Spacer(Modifier.width(8.dp))
            Button(onClick = send, shape = CircleShape, contentPadding = PaddingValues(15.dp)) { Text("➤") }
        }
        Text("Prototype chat uses sample responses, not medical advice.", Modifier.padding(horizontal = 16.dp), color = Muted, fontSize = 10.sp)
    }
}

@Composable
private fun ReminderScreen(scale: Float, text: String, onText: (String) -> Unit, reminders: List<String>, add: () -> Unit, remove: (String) -> Unit) {
    Column(Modifier.fillMaxSize()) {
        Header("My reminders", "Gentle nudges for your daily routine.", scale)
        Row(Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(text, onText, Modifier.weight(1f), placeholder = { Text("Add a reminder") }, shape = RoundedCornerShape(16.dp), singleLine = true)
            Spacer(Modifier.width(8.dp))
            Button(onClick = add, modifier = Modifier.height(54.dp)) { Text("Add") }
        }
        Text("TODAY", Modifier.padding(start = 20.dp, top = 20.dp, bottom = 10.dp), color = Teal, fontWeight = FontWeight.Bold, fontSize = 12.sp)
        LazyColumn(contentPadding = PaddingValues(horizontal = 16.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items(reminders) { item ->
                Card(shape = RoundedCornerShape(16.dp), colors = CardDefaults.cardColors(containerColor = Color.White)) {
                    Row(Modifier.fillMaxWidth().padding(14.dp), verticalAlignment = Alignment.CenterVertically) {
                        Text("◉", color = Teal, fontSize = 23.sp)
                        Text(item, Modifier.weight(1f).padding(horizontal = 12.dp), color = Ink, fontSize = (15 * scale).sp)
                        TextButton(onClick = { remove(item) }) { Text("Done") }
                    }
                }
            }
            if (reminders.isEmpty()) item { Text("No reminders yet. Add one above.", color = Muted) }
        }
    }
}

@Composable
private fun ProfileScreen(scale: Float, elder: Boolean, onElder: (Boolean) -> Unit) {
    LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(bottom = 20.dp), verticalArrangement = Arrangement.spacedBy(14.dp)) {
        item { Header("My profile", "Your account and accessibility settings.", scale) }
        item {
            Panel(Modifier.padding(horizontal = 16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(Modifier.size(62.dp).background(Mint, CircleShape), contentAlignment = Alignment.Center) { Text("☺", color = Teal, fontSize = 34.sp) }
                    Column(Modifier.padding(start = 14.dp)) {
                        Text("HealthVista Member", fontSize = (18 * scale).sp, fontWeight = FontWeight.Bold)
                        Text("Your wellness journey", color = Muted, fontSize = (13 * scale).sp)
                    }
                }
            }
        }
        item {
            Panel(Modifier.padding(horizontal = 16.dp)) {
                Text("Accessibility", fontSize = (19 * scale).sp, fontWeight = FontWeight.Bold)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(Modifier.weight(1f)) {
                        Text("Elder Mode", fontSize = (16 * scale).sp, fontWeight = FontWeight.Bold)
                        Text("Large text, large buttons and simpler navigation", color = Muted, fontSize = (12 * scale).sp)
                    }
                    Switch(checked = elder, onCheckedChange = onElder)
                }
                HorizontalDivider(Modifier.padding(vertical = 10.dp), color = Mint)
                Text("Turn this on for larger text and clearer controls across the app.", color = Muted, fontSize = (13 * scale).sp)
            }
        }
        item {
            Panel(Modifier.padding(horizontal = 16.dp)) {
                Text("About HealthVista", fontSize = (17 * scale).sp, fontWeight = FontWeight.Bold)
                Text("An AI-powered personalized lifestyle and wellness assistant prototype.", color = Muted, fontSize = (13 * scale).sp)
                Text("Version 1.0 · Mini Project", color = Teal, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            }
        }
    }
}
