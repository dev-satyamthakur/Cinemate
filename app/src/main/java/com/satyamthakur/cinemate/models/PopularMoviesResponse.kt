package com.satyamthakur.cinemate.models

data class PopularMoviesResponse(
    val page: Int,
    val results: List<Result>,
    val totalPages: Int,
    val totalResults: Int
)