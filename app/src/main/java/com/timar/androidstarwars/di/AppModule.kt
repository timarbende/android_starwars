@file:OptIn(ExperimentalPagingApi::class)

package com.timar.androidstarwars.di

import android.app.Application
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.room.Room
import com.apollographql.apollo3.ApolloClient
import com.timar.androidstarwars.data.local.LocalDatabase
import com.timar.androidstarwars.data.local.character.CharacterEntity
import com.timar.androidstarwars.data.network.ApolloStarWarsClient
import com.timar.androidstarwars.data.network.ApolloRemoteMediator
import com.timar.androidstarwars.domain.network.StarWarsClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val pageSize = 10

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

    @Provides
    @Singleton
    fun provideLocalDatabase(app: Application): LocalDatabase = Room
        .databaseBuilder(
            app,
            LocalDatabase::class.java,
            "localdb.db"
        ).build()

    @Provides
    @Singleton
    fun provideCharacterPager(localdb: LocalDatabase, swapi: StarWarsClient): Pager<Int, CharacterEntity> {
        return Pager(
            config = PagingConfig(pageSize = pageSize),
            remoteMediator = ApolloRemoteMediator<CharacterEntity>(
                localDatabase = localdb,
                swapi = swapi
            ),
            pagingSourceFactory= {
                localdb.characterDao.pagingSource()
            }
        )
    }
}