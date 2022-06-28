package com.salt.newsappsalt.di

import com.salt.newsappsalt.BuildConfig
import com.salt.newsappsalt.data.remote.ApiNewsServices
import com.salt.newsappsalt.domain.repository.NewsRepositoryImpl
import com.salt.newsappsalt.utils.API_KEY
import com.salt.newsappsalt.utils.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NewsAppNetworkModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor() : HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ) : OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(httpLoggingInterceptor)
            //handle request time out
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .addNetworkInterceptor { chain ->
                val apiKey = API_KEY
                val original = chain.request()
                val request = original.newBuilder()
                    .addHeader("X-Api-Key", apiKey)
                    .build()
                val response = chain.proceed(request)
                response
            }
            .addInterceptor { chain ->
                val original = chain.request()
                val request = original.newBuilder()
                    .build()
                val response = chain.proceed(request)
                response
            }
            .build()
    }



    @Singleton
    @Provides
    fun provideRetrofitConfig(
        client: OkHttpClient
    ) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun provideInteractApiService(retrofit: Retrofit) : ApiNewsServices {
        return retrofit.create(ApiNewsServices::class.java)
    }



}