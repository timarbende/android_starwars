package com.timar.androidstarwars.di

import com.apollographql.apollo3.ApolloClient
import com.timar.androidstarwars.data.network.ApolloStarWarsClient
import com.timar.androidstarwars.domain.network.StarWarsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApolloClient(): ApolloClient = ApolloClient.Builder()
        .serverUrl("https://swapi-graphql.netlify.app/.netlify/functions/index")
        .build()

    @Provides
    @Singleton
    fun provideStarWarsClient(apolloClient: ApolloClient): StarWarsClient = ApolloStarWarsClient(
        apolloClient = apolloClient
    )
}