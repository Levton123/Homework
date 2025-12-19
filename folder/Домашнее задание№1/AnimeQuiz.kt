package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimeQuizApp()
        }
    }
}

@Composable
fun AnimeQuizApp() {
    // Переменные для состояния
    var screen by remember { mutableStateOf("start") }
    var questionIndex by remember { mutableStateOf(0) }
    var selected by remember { mutableStateOf(-1) }
    var score by remember { mutableStateOf(0) }

    // Вопросы
    val q1 = listOf("Наруто", "Блич", "Ван Пис", "Атака титанов")
    val q2 = listOf("Сэйлор Мун", "Кардкаптор Сакура", "Сейлор Мун", "Магическая девочка Мадока")
    val q3 = listOf("Пикачу", "Чаризард", "Мяут", "Иви")
    val q4 = listOf("Драгонболл", "Наруто", "Блич", "Ван Пис")
    val q5 = listOf("Тетрадь смерти", "Токийский гуль", "Стальной алхимик", "Код Гиас")
    val q6 = listOf("Studio Ghibli", "Kyoto Animation", "Toei Animation", "Madhouse")
    val q7 = listOf("Сквозь огонь", "Человек-Бензопила", "Клинок, рассекающий демонов", "Магическая битва")
    val q8 = listOf("Аска Лэнгли", "Рей Аянами", "Синдзи Икари", "Мисато Кацураги")
    val q9 = listOf("Луффи", "Зоро", "Санджи", "Нами")
    val q10 = listOf("Эрен", "Микаса", "Армин", "Леви")

    // Начальный экран
    if (screen == "start") {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Аниме Квиз",
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Проверь свои аниме-знания!",
                    fontSize = 18.sp,
                    color = Color(0xFF424242)
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(24.dp)
                ) {
                    Column {
                        Text(
                            text = "О квизе:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1976D2)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = "10 вопросов об аниме\nПопулярные сериалы и персонажи\nСтудии и создатели",
                            fontSize = 16.sp,
                            color = Color(0xFF424242)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(48.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFF1976D2), RoundedCornerShape(12.dp))
                        .clickable {
                            screen = "quiz"
                            questionIndex = 0
                            score = 0
                            selected = -1
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Начать",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }

    // Экран с вопросами
    if (screen == "quiz") {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFFAFAFA))
                .padding(24.dp)
                .verticalScroll(rememberScrollState())
        ) {
            // Номер вопроса
            Text(
                text = "Вопрос ${questionIndex + 1} из 10",
                fontSize = 16.sp,
                color = Color(0xFF1976D2),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Вопрос
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFBBDEFB), RoundedCornerShape(12.dp))
                    .padding(20.dp)
            ) {
                Text(
                    text = when (questionIndex) {
                        0 -> "Как называется аниме про ниндзя с лисом-демоном внутри?"
                        1 -> "Какое аниме первым популяризировало жанр 'magical girl'?"
                        2 -> "Какой покемон является талисманом франшизы Pokemon?"
                        3 -> "В каком аниме главный герой может превращаться в супер-сайяна?"
                        4 -> "Главный герой какого аниме находит тетрадь, убивающую людей?"
                        5 -> "Какая студия создала 'Унесённые призраками'?"
                        6 -> "Как называется аниме про охотника на демонов с сестрой-демоном?"
                        7 -> "Кто главный герой аниме 'Евангелион'?"
                        8 -> "Как зовут главного героя One Piece, мечтающего стать королём пиратов?"
                        else -> "Как зовут главного героя 'Атаки титанов'?"
                    },
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0D47A1)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Варианты ответов
            val options = when (questionIndex) {
                0 -> q1
                1 -> q2
                2 -> q3
                3 -> q4
                4 -> q5
                5 -> q6
                6 -> q7
                7 -> q8
                8 -> q9
                else -> q10
            }

            options.forEachIndexed { index, text ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            if (selected == index) Color(0xFFBBDEFB) else Color.White,
                            RoundedCornerShape(12.dp)
                        )
                        .clickable { selected = index }
                        .padding(16.dp)
                ) {
                    Text(
                        text = text,
                        fontSize = 16.sp,
                        color = if (selected == index) Color(0xFF0D47A1) else Color(0xFF424242)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Кнопка Дальше
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp)
                    .background(
                        if (selected >= 0) Color(0xFF1976D2) else Color(0xFFBDBDBD),
                        RoundedCornerShape(12.dp)
                    )
                    .clickable(enabled = selected >= 0) {
                        // Правильные ответы: 0, 2, 0, 0, 0, 0, 2, 2, 0, 0
                        val correct = when (questionIndex) {
                            0 -> 0
                            1 -> 2
                            2 -> 0
                            3 -> 0
                            4 -> 0
                            5 -> 0
                            6 -> 2
                            7 -> 2
                            8 -> 0
                            else -> 0
                        }

                        if (selected == correct) {
                            score++
                        }

                        if (questionIndex < 9) {
                            questionIndex++
                            selected = -1
                        } else {
                            screen = "result"
                        }
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (questionIndex == 9) "Завершить" else "Дальше",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }
    }

    // Экран результата
    if (screen == "result") {
        val percent = (score * 100) / 10
        val comment = when {
            percent < 50 -> "Нужно больше смотреть аниме!"
            percent < 80 -> "Неплохо! Продолжай смотреть аниме!"
            else -> "Отлично! Ты настоящий отаку!"
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFE3F2FD))
                .padding(32.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Квиз завершён!",
                    fontSize = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1976D2),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(32.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White, RoundedCornerShape(16.dp))
                        .padding(32.dp)
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Ваш результат",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF424242)
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        Text(
                            text = "$score из 10",
                            fontSize = 48.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1976D2)
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = "$percent%",
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF4CAF50)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color(0xFFFFF9C4), RoundedCornerShape(12.dp))
                        .padding(20.dp)
                ) {
                    Text(
                        text = comment,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center,
                        color = Color(0xFF424242),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(48.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                        .background(Color(0xFF1976D2), RoundedCornerShape(12.dp))
                        .clickable {
                            screen = "start"
                            questionIndex = 0
                            score = 0
                            selected = -1
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Пройти ещё раз",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }
        }
    }
}
