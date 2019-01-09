package com.hsbc.techtest.api

data class CvJson(
    val name: String,
    val title: String,
    val description: String,
    val experience: List<ExperienceJson>,
    val stats: StatsJson
)