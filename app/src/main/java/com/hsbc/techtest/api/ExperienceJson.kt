package com.hsbc.techtest.api

data class ExperienceJson(
    val title: String,
    val dateRange: String,
    val description: String,
    val links: List<LinkJson>
)