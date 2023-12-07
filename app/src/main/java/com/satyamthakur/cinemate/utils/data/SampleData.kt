package com.satyamthakur.cinemate.utils.data

import com.satyamthakur.cinemate.models.BelongsToCollection
import com.satyamthakur.cinemate.models.Genre
import com.satyamthakur.cinemate.models.MovieDetailsResponse
import com.satyamthakur.cinemate.models.ProductionCompany
import com.satyamthakur.cinemate.models.ProductionCountry
import com.satyamthakur.cinemate.models.SpokenLanguage

val exampleMovieDetails = MovieDetailsResponse(
    adult = false,
    backdropPath = "/example_backdrop.jpg",
    belongsToCollection = BelongsToCollection(
        backdropPath = "/collection_backdrop.jpg",
        id = 123,
        name = "Example Collection",
        posterPath = "/collection_poster.jpg"
    ),
    budget = 10000000,
    genres = listOf(Genre(id = 1, name = "Action")),
    homepage = "http://example.com",
    id = 123456,
    imdbId = "tt1234567",
    originalLanguage = "en",
    originalTitle = "The Bad Guys: A Very Bad Holiday",
    overview = "This is an example movie overview. ".repeat(4),
    popularity = 7.8,
    posterPath = "/example_poster.jpg",
    productionCompanies = listOf(
        ProductionCompany(
            id = 1,
            logoPath = "/company_logo.jpg",
            name = "Example Productions",
            originCountry = "US"
        )
    ),
    productionCountries = listOf(ProductionCountry(iso31661 = "US", name = "United States")),
    releaseDate = "2023-01-01",
    revenue = 50000000,
    runtime = 120,
    spokenLanguages = listOf(SpokenLanguage(englishName = "English", iso6391 = "en", name = "English")),
    status = "Released",
    tagline = "An example movie tagline",
    title = "A Holiday",
    video = false,
    voteAverage = 7.5,
    voteCount = 1000
)
