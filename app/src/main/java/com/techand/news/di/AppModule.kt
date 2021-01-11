package com.techand.news.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.techand.news.data.local.AppDatabase
import com.techand.news.data.local.NewsDao
import com.techand.news.data.remote.ApiService
import com.techand.news.data.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit = Retrofit.Builder()
        .baseUrl("https://newsapi.org/v2/")
        .client(OkHttpClient.Builder().also {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)
            it.addInterceptor(logging)
        }.build())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    fun provideNewsService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext appContext: Context) =
        AppDatabase.getInstance(appContext)

    @Singleton
    @Provides
    fun provideCharacterDao(db: AppDatabase) = db.newsDao()

    @Singleton
    @Provides
    fun provideRepository(
        remoteDataSource: ApiService,
        localDataSource: NewsDao
    ) = MainRepository(remoteDataSource, localDataSource)
}